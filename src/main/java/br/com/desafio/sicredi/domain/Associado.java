package br.com.desafio.sicredi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Associado {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String cpf;

	public Associado(){	}
	
	public Associado(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
}