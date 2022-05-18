package br.com.desafio.sicredi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Voto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Associado associado;
	
	@ManyToOne
	private Pauta pauta;

	private String tipo;
	
	public Voto() {}

	public Voto(Associado associado, Pauta pauta, String tipo) {
		this.tipo = tipo;
		this.associado = associado;
		this.pauta = pauta;
	}
}