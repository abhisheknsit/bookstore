package com.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="ISBN does not exist")
public class IsbnDoesNotExistException extends RuntimeException {
	private String message;
	public IsbnDoesNotExistException(Long isbn) {
		super();
		this.message = isbn +" does not exist";
	}
	
	public String getLocalizedMessage() {
		return this.message;
	}

}
