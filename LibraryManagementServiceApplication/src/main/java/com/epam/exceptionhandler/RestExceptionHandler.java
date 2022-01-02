package com.epam.exceptionhandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.epam.exception.DetailsAlreadyExistsException;
import com.epam.exception.DetailsNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {
	String libraryService = "libraryService";
	String library = "library";
	String timestamp = "timestamp";
	String error = "error";
	String status = "status";

	@ExceptionHandler(value = DetailsAlreadyExistsException.class)
	public Map<String, String> handleDetailsAlreadyExistsException(DetailsAlreadyExistsException ex) {
		Map<String, String> response = new HashMap<>();
		response.put(libraryService, library);
		response.put(timestamp, new Date().toString());
		response.put(error, ex.getMessage());
		response.put(status, HttpStatus.NOT_FOUND.name());
		return response;
	}

	@ExceptionHandler(value = DetailsNotFoundException.class)
	public Map<String, String> handleDetailsNotFoundException(DetailsNotFoundException ex) {
		Map<String, String> response = new HashMap<>();
		response.put(libraryService, library);
		response.put(timestamp, new Date().toString());
		response.put(error, ex.getMessage());
		response.put(status, HttpStatus.NOT_FOUND.name());
		return response;
	}

}
