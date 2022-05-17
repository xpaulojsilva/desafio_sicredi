package br.com.desafio.sicredi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.desafio.sicredi.dto.InicializerPautaDto;
import br.com.desafio.sicredi.dto.NewPautaDto;
import br.com.desafio.sicredi.dto.PautaDto;

public interface IPautaService {

	PautaDto createNewPauta(NewPautaDto newPautaDto) throws Exception;

	void updateCompletedPautas();

	PautaDto inicializerPautas(InicializerPautaDto inicializerPautaDto);

	Page<PautaDto> getAllPautas(Pageable pageable);

	PautaDto getPauta(long id);

	ResponseEntity<String> deletePauta(long id);
}