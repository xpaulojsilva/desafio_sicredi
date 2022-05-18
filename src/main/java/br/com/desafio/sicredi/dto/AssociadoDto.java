package br.com.desafio.sicredi.dto;

import org.springframework.beans.BeanUtils;

import br.com.desafio.sicredi.domain.Associado;
import lombok.Data;

@Data
public class AssociadoDto {

	private Long id;

	private String nome;

	private String cpf;
	
	public AssociadoDto() {}
	
	public AssociadoDto(Associado associado) {
		BeanUtils.copyProperties(associado, this);
	}
}