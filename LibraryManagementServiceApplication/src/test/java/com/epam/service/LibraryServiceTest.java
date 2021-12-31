package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.dto.LibraryDto;
import com.epam.entity.Library;
import com.epam.exception.DetailsAlreadyExistsException;
import com.epam.exception.DetailsNotFoundException;
import com.epam.repo.LibraryRepository;

@SpringBootTest
class LibraryServiceTest {

	@Mock
	LibraryRepository libraryRepository;
	
	@InjectMocks
	LibraryService libraryService;
	
	@Mock
	ModelMapper mapper;
	
	@Autowired
	LibraryDto libraryDto;
	
	Library library = new Library();
	
	@BeforeEach
	void setUp() {
		libraryDto.setUsername("username");
		libraryDto.setBookId(12);
	}
	
	@Test
	void addTest() {
		when(mapper.map(libraryDto, Library.class)).thenReturn(library);
		Optional<Library> optionalLibrary = Optional.empty();
		when(libraryRepository.findByUsername(libraryDto.getUsername())).thenReturn(optionalLibrary);
		assertTrue(libraryService.add(libraryDto));
	}
	@Test
	void addErrorTest() {
		Optional<Library> optionalBook = Optional.ofNullable(library);
		when(libraryRepository.findByUsername(libraryDto.getUsername())).thenReturn(optionalBook);
		Throwable exception = assertThrows(DetailsAlreadyExistsException.class, () -> libraryService.add(libraryDto));
		assertEquals("Already Exists", exception.getMessage());
	}
	
	@Test
	void deleteTest() {
		Optional<Library> optionalLibrary = Optional.ofNullable(library);
		when(libraryRepository.findByUsernameAndBookId(libraryDto.getUsername(),libraryDto.getBookId())).thenReturn(optionalLibrary);
		assertTrue(libraryService.delete(libraryDto.getUsername(),libraryDto.getBookId()));
	}
	@Test
	void deleteErrorTest() {
		Optional<Library> optionalBook = Optional.empty();
		when(libraryRepository.findByUsernameAndBookId(libraryDto.getUsername(),libraryDto.getBookId())).thenReturn(optionalBook);
		Throwable exception = assertThrows(DetailsNotFoundException.class, () -> libraryService.delete(libraryDto.getUsername(),libraryDto.getBookId()));
		assertEquals("Details Not Found", exception.getMessage());
	}
	
	
}
