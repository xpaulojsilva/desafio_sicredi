package br.com.desafio.sicredi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.desafio.sicredi.dto.NewVotoDto;
import br.com.desafio.sicredi.dto.VotoDto;
import br.com.desafio.sicredi.service.impl.VotoServiceImp;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/voto")
@ApiOperation(tags = "Voto", value = "Voto", notes = "Voto Operations")
public class VotoController {
	
	@Autowired
	VotoServiceImp votoService;

	@ApiOperation(value = "Create new Voto.")
	@PostMapping
	public ResponseEntity<VotoDto> newVoto(@RequestBody NewVotoDto newVotoDto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(votoService.createNewVoto(newVotoDto));
	}
}