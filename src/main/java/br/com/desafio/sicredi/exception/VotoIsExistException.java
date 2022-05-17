package br.com.desafio.sicredi.exception;

public class VotoIsExistException extends RuntimeException {
	
	private static final long serialVersionUID = -2601007451904587866L;

	public VotoIsExistException(String message) {
		super(message);
	}
}