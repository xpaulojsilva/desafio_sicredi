package br.com.desafio.sicredi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.desafio.sicredi.domain.Associado;
import br.com.desafio.sicredi.dto.AssociadoDto;
import br.com.desafio.sicredi.dto.NewAssociadoDto;
import br.com.desafio.sicredi.exception.AssociadoIsExistException;
import br.com.desafio.sicredi.exception.AssociadoNotFoundException;
import br.com.desafio.sicredi.repository.AssociadoRepository;
import br.com.desafio.sicredi.service.IAssociadoService;

@Service
public class AssociadoServiceImp implements IAssociadoService {

	@Autowired
	AssociadoRepository associadoRepository;

	@Override
	public AssociadoDto createNewAssociado(NewAssociadoDto newAssociadoDto) throws Exception {
		Optional<Associado> associado = associadoRepository.findByCpf(newAssociadoDto.getCpf());
		
		if (associado.isPresent()) 
			throw new AssociadoIsExistException("Associado já existe na base de dados");

		Associado savedAssociado = associadoRepository.save(new Associado(newAssociadoDto.getNome(), newAssociadoDto.getCpf()));
		return new AssociadoDto(savedAssociado);
	}
	
	@Override
	public Page<AssociadoDto> getAllAssociados(Pageable pageable) {
		return associadoRepository.findAll(pageable).map(n -> new AssociadoDto(n));
	}

	@Override
	public AssociadoDto getAssociado(long id) {
		Optional<Associado> associado = associadoRepository.findById(id);
		if(associado.isEmpty()) 
			throw new AssociadoNotFoundException("Associado não existente no banco de dados");
		
		return new AssociadoDto(associado.get());
	}

	@Override
	public ResponseEntity<String> deleteAssociado(long id) {
		Optional<Associado> associado = associadoRepository.findById(id);
		if(associado.isEmpty()) 
			throw new AssociadoNotFoundException("Associado não existente no banco de dados");
		
		associadoRepository.delete(associado.get());
		return new ResponseEntity<>("Associado deletado com sucesso!", HttpStatus.OK);
	}
}