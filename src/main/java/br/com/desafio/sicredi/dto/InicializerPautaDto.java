package br.com.desafio.sicredi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InicializerPautaDto  implements Serializable {

	private static final long serialVersionUID = -6403027455520030528L;

	private Long id;

	public Long minutes;
}