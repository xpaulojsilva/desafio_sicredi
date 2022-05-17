package br.com.desafio.sicredi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.desafio.sicredi.dto.InicializerPautaDto;
import br.com.desafio.sicredi.dto.NewPautaDto;
import br.com.desafio.sicredi.dto.PautaDto;
import br.com.desafio.sicredi.service.impl.PautaServiceImp;
import io.swagger.annotations.ApiOperation;

@Controller
@ApiOperation(tags = "Pauta", value = "Pauta", notes = "Pauta Operations")
@RequestMapping("/pauta")
public class PautaController {
	
	@Autowired
	PautaServiceImp pautaService;

	@ApiOperation(value = "Create new Pauta.")
	@PostMapping
	public ResponseEntity<PautaDto> newPauta(@RequestBody NewPautaDto newPautaDto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.createNewPauta(newPautaDto));
	}
	
	@ApiOperation(value = "Initializer Vonting Session")
	@PostMapping(path = "/iniciar-sessao")
	public ResponseEntity<PautaDto> openSession(@RequestBody InicializerPautaDto inicializerPautaDto) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(pautaService.inicializerPautas(inicializerPautaDto));
	}
	
	@ApiOperation(value = "Return pauta by id.")  
    @GetMapping("/{id}")
	public  ResponseEntity<PautaDto> getPauta(@PathVariable("id") long id) {
		return ResponseEntity.ok(pautaService.getPauta(id));
	}	

	@ApiOperation(value = "Return list of all pautas.")
	@GetMapping("/all")
	public ResponseEntity<Page<PautaDto>> getAllPautas(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "500") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<PautaDto> pautas = pautaService.getAllPautas(pageable);
		return ResponseEntity.ok(pautas);
	}
	
	@ApiOperation(value = "Delete a exists pauta.")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		return pautaService.deletePauta(id);
	}
}