package br.com.desafio.sicredi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.desafio.sicredi.domain.Voto;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VotoDto {

	private Long id;
	
	private long associado;
	
	private long pautaDto;

	private String valor;
	
	public VotoDto() {}

	public VotoDto(Voto voto) {
		this.valor = voto.isValor() ? "Sim" : "Não";
		this.associado = voto.getAssociado().getId();
		this.pautaDto = voto.getPauta().getId();;
	}
}