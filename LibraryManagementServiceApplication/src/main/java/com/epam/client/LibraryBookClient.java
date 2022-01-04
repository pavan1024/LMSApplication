package com.epam.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.epam.dto.BookDto;

@FeignClient(name = "librarybook", url = "http://localhost:9001/books")
public interface LibraryBookClient {

	@GetMapping
	public ResponseEntity<String> getAllBooks();

	@GetMapping("/{id}")
	public ResponseEntity<String> getBook(@PathVariable int id);

	@PostMapping
	public ResponseEntity<String> addBook(BookDto bookDto);

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id);

	@PutMapping("/{id}")
	public ResponseEntity<String> updateBook(@PathVariable int id, BookDto bookDto);

}