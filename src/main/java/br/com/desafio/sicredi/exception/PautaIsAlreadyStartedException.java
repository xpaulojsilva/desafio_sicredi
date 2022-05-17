package br.com.desafio.sicredi.exception;

public class PautaIsAlreadyStartedException extends RuntimeException {

	private static final long serialVersionUID = 5002258735000516808L;

	public PautaIsAlreadyStartedException(String message) {
		super(message);
	}
}