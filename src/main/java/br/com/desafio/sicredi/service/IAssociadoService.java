package br.com.desafio.sicredi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.desafio.sicredi.dto.AssociadoDto;
import br.com.desafio.sicredi.dto.NewAssociadoDto;

public interface IAssociadoService {

	AssociadoDto createNewAssociado(NewAssociadoDto newAssociadoDto) throws Exception;

	AssociadoDto getAssociado(long id);

	ResponseEntity<String> deleteAssociado(long id);

	Page<AssociadoDto> getAllAssociados(Pageable pageable);
}