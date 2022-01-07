package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.dto.LibraryDto;
import com.epam.entity.Library;
import com.epam.exception.BookAlreadyIssuedException;
import com.epam.exception.BookDetailsNotFoundException;
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
		when(mapper.map(libraryDto, Library.class)).thenReturn(library);
		when(libraryRepository.save(library)).thenReturn(library);
		when(libraryRepository.existsByBookId(libraryDto.getBookId())).thenReturn(false);
		assertEquals(libraryDto, libraryService.addLibraryDetails(libraryDto));

	}

	@Test
	void addErrorTest() {
		when(libraryRepository.existsByBookId(libraryDto.getBookId())).thenReturn(true);
		Throwable exception = assertThrows(BookAlreadyIssuedException.class,
				() -> libraryService.addLibraryDetails(libraryDto));
		assertEquals("Book Already Issued", exception.getMessage());

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
		Throwable exception = assertThrows(BookDetailsNotFoundException.class,
				() -> libraryService.deleteLibraryDetails(libraryDto.getUsername(), libraryDto.getBookId()));
		assertEquals("Book Details Not Found", exception.getMessage());
	}

}
