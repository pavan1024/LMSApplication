package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.dto.BookDto;

@RestController
@RequestMapping("/library/books")
public class LibraryBookController {
	@Autowired
	RestTemplate template;

	String booksUrl = "http://localhost:9001/books";

	@GetMapping
	public ResponseEntity<String> getAllBooks() {
		return template.exchange(booksUrl, HttpMethod.GET, null, String.class);
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getBook(@PathVariable int id) {
		String url = booksUrl.concat("/" + id);
		return template.exchange(url, HttpMethod.GET, null, String.class);
	}

	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
		HttpEntity<BookDto> book = new HttpEntity<>(bookDto);
		return template.exchange(booksUrl, HttpMethod.POST, book, String.class);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id) {
		String url = booksUrl.concat("/" + id);
		return template.exchange(url, HttpMethod.DELETE, null, String.class);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody BookDto bookDto) {
		String url = booksUrl.concat("/" + id);
		HttpEntity<BookDto> book = new HttpEntity<>(bookDto);
		return template.exchange(url, HttpMethod.PUT, book, String.class);
	}
}