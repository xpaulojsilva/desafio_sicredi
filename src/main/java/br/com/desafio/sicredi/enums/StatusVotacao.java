package br.com.desafio.sicredi.enums;

import lombok.Getter;

public enum StatusVotacao {

	AGUARDANDO("AGUARDANDO"),
	INICIADA("INICIADA"),
	FINALIZADA("FINALIZADA");
	
	StatusVotacao(String status) {
		this.status = status;
	}

	@Getter
	private String status;
}	