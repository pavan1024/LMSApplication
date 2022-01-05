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
import com.epam.client.BookClient;
import com.epam.dto.BookDto;

@RestController
@RequestMapping("/library/books")
public class LibraryBookController {

	@Autowired
	BookClient bookClient;	

	@GetMapping
	public ResponseEntity<String> getAllBooks() {
		return bookClient.getAllBooks();
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getBook(@PathVariable int id) {
		return bookClient.getBook(id);
	}

	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
		return bookClient.addBook(bookDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id) {
		return bookClient.deleteBook(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody BookDto bookDto) {
		return bookClient.updateBook(id, bookDto);
	}

}