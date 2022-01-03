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
		LibraryDto libraryDto1;
		Optional<Library> library2 = libraryRepository.findByBookId(libraryDto.getBookId());
		Library library = mapper.map(libraryDto, Library.class);		
		if (library2.isPresent()) {
			throw new DetailsAlreadyExistsException("Already Exists");
		} else {
			libraryRepository.save(library);
			libraryDto1 = mapper.map(library, LibraryDto.class);
		}
		return libraryDto1;
	}

	public String deleteLibraryDetails(String username, int bookId) throws DetailsNotFoundException {
		String status = "";
		Optional<Library> user = libraryRepository.findByUsernameAndBookId(username, bookId);
		if (user.isPresent()) {
			libraryRepository.delete(user.get());
			status = "Deleted Library Details";
		} else {
			throw new DetailsNotFoundException("Details Not Found");
		}
		return status;
	}

}
