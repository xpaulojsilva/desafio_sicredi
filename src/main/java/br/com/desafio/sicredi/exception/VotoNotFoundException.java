package br.com.desafio.sicredi.exception;

public class VotoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5002258735000516808L;

	public VotoNotFoundException(String message) {
		super(message);
	}
}