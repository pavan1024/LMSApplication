package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.LibraryDto;
import com.epam.exception.DetailsAlreadyExistsException;
import com.epam.exception.DetailsNotFoundException;
import com.epam.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	LibraryService libraryService;
	
	@Autowired
	LibraryDto libraryDto;
	
	@PostMapping("/users/{username}/books/{bookId}")
	public ResponseEntity<String> add(@PathVariable String username,@PathVariable int bookId) throws DetailsAlreadyExistsException{
		String status = "";
		HttpStatus statusCode = null;
		libraryDto.setUsername(username);
		libraryDto.setBookId(bookId);
		if (libraryService.add(libraryDto)) {
			status = "Library Details Added Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Library Addition Unsuccessful";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}
	
	@DeleteMapping("/users/{username}/books/{bookId}")
	public ResponseEntity<String> delete(@PathVariable String username,@PathVariable int bookId) throws DetailsNotFoundException{
		String status = "";
		HttpStatus statusCode = null;
		if (libraryService.delete(username,bookId)) {
			status = "Library Details Deleted Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Library Deletion Unsuccessful";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}
}
