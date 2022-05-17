package br.com.desafio.sicredi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewAssociadoDto  implements Serializable {

	private static final long serialVersionUID = 909310961320642689L;
	
	private String nome;
	
	private String cpf;
}