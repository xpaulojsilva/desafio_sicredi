package br.com.desafio.sicredi.enums;

import lombok.Getter;

public enum TipoVoto {

	SIM("Sim"),
	NAO("Não");
	
	TipoVoto(String tipo) {
		this.tipo = tipo;
	}

	@Getter
	private String tipo;
}