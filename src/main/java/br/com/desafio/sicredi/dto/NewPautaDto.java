package br.com.desafio.sicredi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewPautaDto  implements Serializable {
	
	private static final long serialVersionUID = 2940306081136905770L;

	private String nome;
}