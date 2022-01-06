package com.epam.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.epam.dto.BookDto;

@FeignClient(name = "book-service" )
public interface BookClient {

	@GetMapping("/books")
	public ResponseEntity<String> getAllBooks();

	@GetMapping("/books/{id}")
	public ResponseEntity<String> getBook(@PathVariable int id);

	@PostMapping("/books")
	public ResponseEntity<String> addBook(BookDto bookDto);

	@DeleteMapping("/books/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id);

	@PutMapping("/books/{id}")
	public ResponseEntity<String> updateBook(@PathVariable int id, BookDto bookDto);

}