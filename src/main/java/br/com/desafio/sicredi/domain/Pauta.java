package br.com.desafio.sicredi.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.desafio.sicredi.enums.StatusVotacao;
import lombok.Data;

@Data
@Entity
public class Pauta {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private LocalDateTime inicio;

	private LocalDateTime fim;
	
	private long votosAFavor;

	private long votosContra;
	
	private String status;
	
	public Pauta() {
		this.status = StatusVotacao.AGUARDANDO.getStatus();
	}
	
	public Pauta(String nome) {
		this.status = StatusVotacao.AGUARDANDO.getStatus();
		this.nome = nome;
	}
}