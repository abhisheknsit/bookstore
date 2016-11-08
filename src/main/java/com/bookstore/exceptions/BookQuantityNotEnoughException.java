package com.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Error in Quantity Supplied")
public class BookQuantityNotEnoughException extends RuntimeException {
	private String message;
	public BookQuantityNotEnoughException(Integer q) {
		super();
		this.message = "Error in quantity "+q;
	}
	
	public String getLocalizedMessage() {
		return this.message;
	}
}
