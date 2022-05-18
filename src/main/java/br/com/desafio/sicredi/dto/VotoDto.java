package br.com.desafio.sicredi.dto;

import br.com.desafio.sicredi.domain.Voto;
import lombok.Data;

@Data
public class VotoDto {

	private Long id;
	
	private long associado;
	
	private long pautaDto;

	private String tipo;
	
	public VotoDto() {}

	public VotoDto(Voto voto) {
		this.id = voto.getId();
		this.tipo = voto.getTipo();
		this.associado = voto.getAssociado().getId();
		this.pautaDto = voto.getPauta().getId();;
	}
}