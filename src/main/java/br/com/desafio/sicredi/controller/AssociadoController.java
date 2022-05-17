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

import br.com.desafio.sicredi.dto.AssociadoDto;
import br.com.desafio.sicredi.dto.NewAssociadoDto;
import br.com.desafio.sicredi.service.impl.AssociadoServiceImp;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/associado")
@ApiOperation(tags = "Associado", value = "Associado", notes = "Associado Operations")
public class AssociadoController {
	
	@Autowired
	AssociadoServiceImp associadoService;

	@ApiOperation(value = "Create new Associado.")
	@PostMapping
	public ResponseEntity<AssociadoDto> newAssociado(@RequestBody NewAssociadoDto newAssociadoDto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(associadoService.createNewAssociado(newAssociadoDto));
	}
	
	@ApiOperation(value = "Return associado by id.")  
    @GetMapping("/{id}")
	public  ResponseEntity<AssociadoDto> getAssociado(@PathVariable("id") long id) {
		return ResponseEntity.ok(associadoService.getAssociado(id));
	}	

	@ApiOperation(value = "Return list of all associados.")
	@GetMapping("/all")
	public ResponseEntity<Page<AssociadoDto>> getAllAssociados(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "500") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<AssociadoDto> associados = associadoService.getAllAssociados(pageable);
		return ResponseEntity.ok(associados);
	}
	
	@ApiOperation(value = "Delete a exists associado.")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		return associadoService.deleteAssociado(id);
	}
}