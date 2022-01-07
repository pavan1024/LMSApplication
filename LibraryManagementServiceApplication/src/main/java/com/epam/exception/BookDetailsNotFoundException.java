package com.epam.exception;

public class BookDetailsNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BookDetailsNotFoundException(String message) {
		super(message);
	}
}
