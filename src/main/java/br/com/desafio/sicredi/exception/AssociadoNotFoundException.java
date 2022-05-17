package br.com.desafio.sicredi.exception;

public class AssociadoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5002258735000516808L;

	public AssociadoNotFoundException(String message) {
		super(message);
	}
}