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
import com.epam.exception.BookAlreadyIssuedException;
import com.epam.exception.BookDetailsNotFoundException;
import com.epam.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	LibraryService libraryService;

	@PostMapping("/users/{username}/books/{bookId}")
	public ResponseEntity<LibraryDto> addLibraryDetails(@PathVariable String username, @PathVariable int bookId)
			throws BookAlreadyIssuedException {
		LibraryDto libraryDto = new LibraryDto();
		libraryDto.setUsername(username);
		libraryDto.setBookId(bookId);
		return new ResponseEntity<>(libraryService.addLibraryDetails(libraryDto), HttpStatus.CREATED);
	}

	@DeleteMapping("/users/{username}/books/{bookId}")
	public ResponseEntity<String> deleteLibraryDetails(@PathVariable String username, @PathVariable int bookId)
			throws BookDetailsNotFoundException {
		return new ResponseEntity<>(libraryService.deleteLibraryDetails(username, bookId), HttpStatus.NO_CONTENT);
	}
}
