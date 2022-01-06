package com.epam.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.epam.dto.UserDto;

@FeignClient(name = "user-service")
public interface UserClient {
	
	@GetMapping("/users")
	public ResponseEntity<String> getAllUsers();

	@GetMapping("/users/{username}")
	public ResponseEntity<String> getUser(@PathVariable String username);

	@PostMapping("/users")
	public ResponseEntity<String> addUser(UserDto userDto);

	@DeleteMapping("/users/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username);

	@PutMapping("/users/{username}")
	public ResponseEntity<String> updateUser(@PathVariable String username, UserDto userDto);
}
