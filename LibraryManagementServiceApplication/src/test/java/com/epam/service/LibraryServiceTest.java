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
	
	LibraryDto libraryDto;
	Library library;

	@BeforeEach
	void setUp() {
		library = new Library();
		libraryDto = new LibraryDto();
		libraryDto.setUsername("username");
		libraryDto.setBookId(12);
		
	}

	@Test
	void addTest() {
		Optional<Library> optionalLibrary = Optional.empty();
		when(mapper.map(libraryDto, Library.class)).thenReturn(library);
		when(libraryRepository.findByBookId(libraryDto.getBookId())).thenReturn(optionalLibrary);
		assertEquals(null, libraryService.addLibraryDetails(libraryDto));

	}

	@Test
	void addErrorTest() {
		Optional<Library> optionalBook = Optional.ofNullable(library);
		when(libraryRepository.findByBookId(libraryDto.getBookId())).thenReturn(optionalBook);
		Throwable exception = assertThrows(DetailsAlreadyExistsException.class,
				() -> libraryService.addLibraryDetails(libraryDto));
		assertEquals("Already Exists", exception.getMessage());

	}

	@Test
	void deleteTest() {
		Optional<Library> optionalLibrary = Optional.ofNullable(library);
		when(libraryRepository.findByUsernameAndBookId(libraryDto.getUsername(), libraryDto.getBookId()))
				.thenReturn(optionalLibrary);
		assertEquals("Deleted Library Details",
				libraryService.deleteLibraryDetails(libraryDto.getUsername(), libraryDto.getBookId()));
	}

	@Test
	void deleteErrorTest() {
		Optional<Library> optionalBook = Optional.empty();
		when(libraryRepository.findByUsernameAndBookId(libraryDto.getUsername(), libraryDto.getBookId()))
				.thenReturn(optionalBook);
		Throwable exception = assertThrows(DetailsNotFoundException.class,
				() -> libraryService.deleteLibraryDetails(libraryDto.getUsername(), libraryDto.getBookId()));
		assertEquals("Details Not Found", exception.getMessage());
	}

}
