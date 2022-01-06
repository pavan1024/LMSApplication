package com.epam.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.LibraryDto;
import com.epam.entity.Library;
import com.epam.exception.DetailsAlreadyExistsException;
import com.epam.exception.DetailsNotFoundException;
import com.epam.repo.LibraryRepository;

@Service
public class LibraryService {

	@Autowired
	LibraryRepository libraryRepository;

	@Autowired
	ModelMapper mapper;

	public LibraryDto addLibraryDetails(LibraryDto libraryDto) throws DetailsAlreadyExistsException {
		Optional<Library> retrievedLibrary = libraryRepository.findByBookId(libraryDto.getBookId());
		if (retrievedLibrary.isPresent()) {
			throw new DetailsAlreadyExistsException("Already Exists");
		}
		Library library = mapper.map(libraryDto, Library.class);
		library = libraryRepository.save(library);
		libraryDto.setId(library.getId());
		return libraryDto;
	}

	public String deleteLibraryDetails(String username, int bookId) throws DetailsNotFoundException {
		Library library = libraryRepository.findByUsernameAndBookId(username, bookId)
				.orElseThrow(() -> new DetailsNotFoundException("Details Not Found"));
		libraryRepository.delete(library);
		return "Deleted Library Details";
	}

}
