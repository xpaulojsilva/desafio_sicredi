package br.com.desafio.sicredi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.desafio.sicredi.domain.Pauta;
import br.com.desafio.sicredi.domain.Voto;
import br.com.desafio.sicredi.dto.InicializerPautaDto;
import br.com.desafio.sicredi.dto.NewPautaDto;
import br.com.desafio.sicredi.dto.PautaDto;
import br.com.desafio.sicredi.enums.StatusVotacao;
import br.com.desafio.sicredi.enums.TipoVoto;
import br.com.desafio.sicredi.exception.PautaIsAlreadyStartedException;
import br.com.desafio.sicredi.exception.PautaIsExistException;
import br.com.desafio.sicredi.exception.PautaNotFoundException;
import br.com.desafio.sicredi.repository.PautaRepository;
import br.com.desafio.sicredi.repository.VotoRepository;
import br.com.desafio.sicredi.service.IPautaService;

@Service
public class PautaServiceImp implements IPautaService {

	@Autowired
	PautaRepository pautaRepository;
	
	@Autowired
	VotoRepository votoRepository;

	@Override
	public PautaDto createNewPauta(NewPautaDto newPautaDto) throws Exception {
		if (pautaRepository.findByNome(newPautaDto.getNome()).isPresent()) throw new PautaIsExistException("Pauta já existe na base de dados");

		return new PautaDto( pautaRepository.save(new Pauta(newPautaDto.getNome())));
	}

	@Override
	public PautaDto inicializerPautas(InicializerPautaDto inicializerPautaDto) {
		if(pautaRepository.findById(inicializerPautaDto.getId()).isEmpty())	
			throw new PautaNotFoundException("Pauta não existente no banco de dados");
		
		Pauta pautaInitializer = pautaRepository.findById(inicializerPautaDto.getId()).get();
		
		if(!pautaInitializer.getStatus().equals(StatusVotacao.AGUARDANDO.getStatus())) 
			throw new PautaIsAlreadyStartedException("A Pauta já havia sido iniciada");
		
		pautaInitializer.setStatus(StatusVotacao.INICIADA.getStatus());
		pautaInitializer.setInicio(LocalDateTime.now());
		
		long minutesAdd = inicializerPautaDto.getMinutes() < 1 ? 1 : inicializerPautaDto.getMinutes();
		pautaInitializer.setFim(pautaInitializer.getInicio().plusMinutes(minutesAdd));
		
		return new PautaDto(pautaRepository.save(pautaInitializer));
	}
	
	@Override
	public void updateCompletedPautas() {
		List<Pauta> pautas = pautaRepository.findCompletedPautas(LocalDateTime.now(), StatusVotacao.INICIADA.getStatus());

		if(pautas.isEmpty()) return;

		pautas.stream().forEach(pauta -> {
			List<Voto> votos = votoRepository.findByPautaId(pauta.getId());
			if (!votos.isEmpty()) {
				int size = votos.size();
				long votosAFavor = votos.stream().filter(v -> v.getTipo().equals(TipoVoto.SIM.getTipo())).count();
				pauta.setVotosAFavor(votosAFavor);
				pauta.setVotosContra(size - votosAFavor);
			}
							
			pauta.setStatus(StatusVotacao.FINALIZADA.getStatus());
			pautaRepository.save(pauta);
		});
	}

	@Override
	public Page<PautaDto> getAllPautas(Pageable pageable) {
		return pautaRepository.findAll(pageable).map(n -> new PautaDto(n));
	}

	@Override
	public PautaDto getPauta(long id) {
		Optional<Pauta> pauta = pautaRepository.findById(id);
		if(pauta.isEmpty()) 
			throw new PautaNotFoundException("Pauta não existente no banco de dados");
		
		return new PautaDto(pauta.get());
	}

	@Override
	public ResponseEntity<String> deletePauta(long id) {
		Optional<Pauta> pauta = pautaRepository.findById(id);
		if(pauta.isEmpty()) 
			throw new PautaNotFoundException("Pauta não existente no banco de dados");
		
		pautaRepository.delete(pauta.get());
		return new ResponseEntity<>("Pauta deletada com sucesso!", HttpStatus.OK);
	}

	@Override
	public Page<PautaDto> getAllFinalizedPautas(Pageable pageable) {
		return pautaRepository.findByStatus(StatusVotacao.FINALIZADA.getStatus(), pageable).map(n -> new PautaDto(n));
	}
}