package br.com.desafio.sicredi.exception;

public class AssociadoIsExistException extends RuntimeException {
	
	private static final long serialVersionUID = -2601007451904587866L;

	public AssociadoIsExistException(String message) {
		super(message);
	}
}