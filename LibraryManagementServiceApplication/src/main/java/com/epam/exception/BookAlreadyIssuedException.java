package com.epam.exception;

public class DetailsAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DetailsAlreadyExistsException(String message) {
		super(message);
	}

}
