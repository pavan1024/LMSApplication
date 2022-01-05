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
import com.epam.client.UserClient;
import com.epam.dto.UserDto;

@RestController
@RequestMapping("/library/users")
public class LibraryUserController {
	
	@Autowired
	UserClient userClient;

	@GetMapping
	public ResponseEntity<String> getAllUsers() {
		return userClient.getAllUsers();
	}

	@GetMapping("/{username}")
	public ResponseEntity<String> getUser(@PathVariable String username) {
		return userClient.getUser(username);
	}

	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody UserDto userDto) {
		return userClient.addUser(userDto);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<String> deleteBook(@PathVariable String username) {
		return userClient.deleteUser(username);
	}

	@PutMapping("/{username}")
	public ResponseEntity<String> updateBook(@PathVariable String username, @RequestBody UserDto userDto) {
		return userClient.updateUser(username, userDto);
	}

}
