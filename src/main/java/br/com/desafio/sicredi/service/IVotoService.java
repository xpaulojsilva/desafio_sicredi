package br.com.desafio.sicredi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.desafio.sicredi.dto.NewVotoDto;
import br.com.desafio.sicredi.dto.VotoDto;

public interface IVotoService {

	VotoDto createNewVoto(NewVotoDto newVotoDto) throws Exception;

	VotoDto getVoto(long id);

	Page<VotoDto> getAllVotos(Pageable pageable);
} 