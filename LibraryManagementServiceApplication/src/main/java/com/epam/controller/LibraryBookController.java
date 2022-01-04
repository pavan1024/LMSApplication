package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.epam.client.LibraryBookClient;
import com.epam.dto.BookDto;

@RestController
@RequestMapping("/library/books")
public class LibraryBookController {

	@Autowired
	LibraryBookClient libraryBookClient;	

	@GetMapping
	public ResponseEntity<String> getAllBooks() {
		return libraryBookClient.getAllBooks();
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getBook(@PathVariable int id) {
		return libraryBookClient.getBook(id);
	}

	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
		return libraryBookClient.addBook(bookDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id) {
		return libraryBookClient.deleteBook(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody BookDto bookDto) {
		return libraryBookClient.updateBook(id, bookDto);
	}

}