package br.com.desafio.sicredi.exception;

public class VotingSessionIsClosedException extends RuntimeException {
	
	private static final long serialVersionUID = -2601007451904587866L;

	public VotingSessionIsClosedException(String message) {
		super(message);
	}
}