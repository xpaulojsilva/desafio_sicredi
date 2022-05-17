package br.com.desafio.sicredi.exception;

import lombok.Data;

@Data
public class StandardError {
  
  	private Integer status_code;
	
    private String message;
    
    public StandardError(int value, String message) {
    	this.status_code = value;
    	this.message = message;
    }
}