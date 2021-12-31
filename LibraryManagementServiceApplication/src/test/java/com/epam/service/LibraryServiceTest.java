package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.entity.Library;
import com.epam.repo.LibraryRepository;

@SpringBootTest
class LibraryServiceTest {

	@Mock
	LibraryRepository libraryRepository;
	
	@InjectMocks
	LibraryService libraryService;
	
	@Autowired
	Library library;
	
	@BeforeEach
	void setUp() {
		library.setUsername("username");
		library.setBookId(12);
	}
	
//	@Test
//	void addTest() {
//		Optional<Library> optionalLibrary = Optional.empty();
//		when(libraryRepository.findByUsername(library.getUsername())).thenReturn(optionalLibrary);
//		assertTrue(libraryService.add(library));
//		
//	}
	
}
