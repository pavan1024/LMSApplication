package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.dto.UserDto;

@RestController
@RequestMapping("/library/users")
public class LibraryUserController {
	@Autowired
	RestTemplate template;

	String usersUrl = "http://localhost:9002/users";

	@GetMapping
	public ResponseEntity<String> getAllUsers() {
		return template.exchange(usersUrl, HttpMethod.GET, null, String.class);
	}

	@GetMapping("/{username}")
	public ResponseEntity<String> getUser(@PathVariable String username) {
		String url = usersUrl.concat("/" + username);
		return template.exchange(url, HttpMethod.GET, null, String.class);
	}

	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody UserDto userDto) {
		HttpEntity<UserDto> user = new HttpEntity<>(userDto);
		return template.exchange(usersUrl, HttpMethod.POST, user, String.class);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<String> deleteBook(@PathVariable String username) {
		String url = usersUrl.concat("/" + username);
		return template.exchange(url, HttpMethod.DELETE, null, String.class);
	}

}
