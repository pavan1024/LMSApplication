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
import com.epam.client.LibraryUserClient;
import com.epam.dto.UserDto;

@RestController
@RequestMapping("/library/users")
public class LibraryUserController {
	
	@Autowired
	LibraryUserClient libraryUserClient;

	@GetMapping
	public ResponseEntity<String> getAllUsers() {
		return libraryUserClient.getAllUsers();
	}

	@GetMapping("/{username}")
	public ResponseEntity<String> getUser(@PathVariable String username) {
		return libraryUserClient.getUser(username);
	}

	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody UserDto userDto) {
		return libraryUserClient.addUser(userDto);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<String> deleteBook(@PathVariable String username) {
		return libraryUserClient.deleteUser(username);
	}

	@PutMapping("/{username}")
	public ResponseEntity<String> updateBook(@PathVariable String username, @RequestBody UserDto userDto) {
		return libraryUserClient.updateUser(username, userDto);
	}

}
