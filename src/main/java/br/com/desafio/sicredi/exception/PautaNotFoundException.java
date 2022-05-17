package br.com.desafio.sicredi.exception;

public class PautaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5002258735000516808L;

	public PautaNotFoundException(String message) {
		super(message);
	}
}