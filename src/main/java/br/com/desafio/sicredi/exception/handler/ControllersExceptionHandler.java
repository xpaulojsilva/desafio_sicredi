package br.com.desafio.sicredi.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.desafio.sicredi.exception.AssociadoIsExistException;
import br.com.desafio.sicredi.exception.AssociadoNotFoundException;
import br.com.desafio.sicredi.exception.PautaIsAlreadyStartedException;
import br.com.desafio.sicredi.exception.PautaIsExistException;
import br.com.desafio.sicredi.exception.PautaNotFoundException;
import br.com.desafio.sicredi.exception.StandardError;
import br.com.desafio.sicredi.exception.VotingSessionIsClosedException;
import br.com.desafio.sicredi.exception.VotoIsExistException;
import br.com.desafio.sicredi.exception.VotoNotFoundException;

@RestControllerAdvice
public class ControllersExceptionHandler {

	@ExceptionHandler(AssociadoIsExistException.class)
	public ResponseEntity<StandardError> objectConflict(AssociadoIsExistException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(PautaIsExistException.class)
	public ResponseEntity<StandardError> objectConflict(PautaIsExistException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(VotoIsExistException.class)
	public ResponseEntity<StandardError> objectConflict(VotoIsExistException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(PautaNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(PautaNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(VotoNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(VotoNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(AssociadoNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(AssociadoNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(VotingSessionIsClosedException.class)
	public ResponseEntity<StandardError> objectNotFound(VotingSessionIsClosedException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.LOCKED.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.LOCKED).body(err);
	}
	
	@ExceptionHandler(PautaIsAlreadyStartedException.class)
	public ResponseEntity<StandardError> objectNotFound(PautaIsAlreadyStartedException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> objectNotFound(IllegalArgumentException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}