package br.com.desafio.sicredi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewVotoDto  implements Serializable {

	private static final long serialVersionUID = 6573647668039505413L;
	
	private long associado;
	
	private long pauta;

	private String valor;
}