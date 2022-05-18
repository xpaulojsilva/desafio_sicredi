package br.com.desafio.sicredi.dto;

import lombok.Data;

@Data
public class NewVotoDto {

	private long associado;
	
	private long pauta;

	private String valor;
}