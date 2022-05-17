package br.com.desafio.sicredi.exception;

public class PautaIsExistException extends RuntimeException {
	
	private static final long serialVersionUID = -2601007451904587866L;

	public PautaIsExistException(String message) {
		super(message);
	}
}