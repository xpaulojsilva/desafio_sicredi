package br.com.desafio.sicredi.dto;

import java.time.format.DateTimeFormatter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

import br.com.desafio.sicredi.domain.Pauta;
import lombok.Data;

@Data
public class PautaDto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String inicio;

	private String fim;
	
	private long votosAFavor;

	private long votosContra;
	
	private String status;
	
	public PautaDto() {}
	
	public PautaDto(Pauta pauta) {
		BeanUtils.copyProperties(pauta, this);
		BeanUtils.copyProperties(pauta, this, "inicio", "fim");
		
		if (pauta.getInicio() != null) 
			this.inicio = pauta.getInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		
		if (pauta.getFim() != null) 
				this.fim = pauta.getFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
	
	public PautaDto(String nome) {
		this.nome = nome;
	}
}