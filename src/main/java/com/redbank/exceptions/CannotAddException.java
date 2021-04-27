package com.redbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CannotAddException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CannotAddException(String message) {
		super(message);
	}

}
