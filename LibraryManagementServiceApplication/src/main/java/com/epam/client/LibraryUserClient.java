package com.epam.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.epam.dto.UserDto;

@FeignClient(name = "libraryuser", url = "http://localhost:9002/users")
public interface LibraryUserClient {
	
	@GetMapping
	public ResponseEntity<String> getAllUsers();

	@GetMapping("/{username}")
	public ResponseEntity<String> getUser(@PathVariable String username);

	@PostMapping
	public ResponseEntity<String> addUser(UserDto userDto);

	@DeleteMapping("/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username);

	@PutMapping("/{username}")
	public ResponseEntity<String> updateUser(@PathVariable String username, UserDto userDto);
}
