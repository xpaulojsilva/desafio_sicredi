package br.com.desafio.sicredi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.desafio.sicredi.domain.Associado;
import br.com.desafio.sicredi.domain.Pauta;
import br.com.desafio.sicredi.domain.Voto;
import br.com.desafio.sicredi.dto.NewVotoDto;
import br.com.desafio.sicredi.dto.VotoDto;
import br.com.desafio.sicredi.enums.StatusVotacao;
import br.com.desafio.sicredi.exception.AssociadoNotFoundException;
import br.com.desafio.sicredi.exception.PautaNotFoundException;
import br.com.desafio.sicredi.exception.VotingSessionIsClosedException;
import br.com.desafio.sicredi.exception.VotoIsExistException;
import br.com.desafio.sicredi.exception.VotoNotFoundException;
import br.com.desafio.sicredi.repository.AssociadoRepository;
import br.com.desafio.sicredi.repository.PautaRepository;
import br.com.desafio.sicredi.repository.VotoRepository;
import br.com.desafio.sicredi.service.IVotoService;

@Service
public class VotoServiceImp implements IVotoService {

	@Autowired
	VotoRepository votoRepository;
	
	@Autowired
	AssociadoRepository associadoRepository;
	
	@Autowired
	PautaRepository pautaRepository;

	@Override
	public VotoDto createNewVoto(NewVotoDto newVotoDto) throws Exception {
		Optional<Voto> voto = votoRepository.findByAssociadoIdAndPautaId(newVotoDto.getAssociado(), newVotoDto.getPauta());
		
		if (voto.isPresent()) 
			throw new VotoIsExistException("O associoado: " + voto.get().getAssociado().getNome() + " já votou para pauta: " + voto.get().getPauta().getNome() + "!");
		
		Optional<Pauta> pauta = pautaRepository.findById(newVotoDto.getPauta());
		
		if(pauta.isEmpty()) {
			throw new PautaNotFoundException("Pauta não existente no banco de dados");
		
		} else if(pauta.get().getStatus().equals(StatusVotacao.AGUARDANDO)) {
			throw new VotingSessionIsClosedException("A sessão de votação para pauta: " + pauta.get().getStatus() + " ainda não foi inicializada");
			
		} else if(pauta.get().getStatus().equals(StatusVotacao.FINALIZADA)) {
			throw new VotingSessionIsClosedException("A sessão de votação para pauta: " + pauta.get().getStatus() + " está finalizada");
		}
		
		Optional<Associado> associado = associadoRepository.findById(newVotoDto.getAssociado());
		
		if(associado.isEmpty()) 
			throw new AssociadoNotFoundException("Associado não existente no banco de dados");
		
		if(!newVotoDto.getValor().equals("Sim") && !newVotoDto.getValor().equals("Não")) 
			throw new IllegalArgumentException("Os valores aceitos para votos são apenas 'Sim'/'Não'!");

		Voto savedVoto = votoRepository.save(new Voto(associado.get(), pauta.get(), newVotoDto.getValor().equals("Sim")));
		return new VotoDto(savedVoto);
	}
	
	@Override
	public Page<VotoDto> getAllVotos(Pageable pageable) {
		return votoRepository.findAll(pageable).map(n -> new VotoDto(n));
	}

	@Override
	public VotoDto getVoto(long id) {
		Optional<Voto> voto = votoRepository.findById(id);
		if(voto.isEmpty()) throw new VotoNotFoundException("Voto não existente no banco de dados");
		
		return new VotoDto(voto.get());
	}
}