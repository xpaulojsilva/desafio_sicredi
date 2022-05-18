package br.com.desafio.sicredi.dto;

import br.com.desafio.sicredi.domain.Voto;
import lombok.Data;

@Data
public class VotoDto {

	private Long id;
	
	private long associado;
	
	private long pautaDto;

	private String valor;
	
	public VotoDto() {}

	public VotoDto(Voto voto) {
		this.id = voto.getId();
		this.valor = voto.isValor() ? "Sim" : "Não";
		this.associado = voto.getAssociado().getId();
		this.pautaDto = voto.getPauta().getId();;
	}
}