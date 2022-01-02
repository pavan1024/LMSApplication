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

	public boolean addLibraryDetails(LibraryDto libraryDto) throws DetailsAlreadyExistsException {
		boolean status = false;
		Optional<Library> username = libraryRepository.findByUsername(libraryDto.getUsername());
		Optional<Library> bookId = libraryRepository.findByBookId(libraryDto.getBookId());
		Library library = mapper.map(libraryDto, Library.class);
		if (username.isPresent() || bookId.isPresent()) {
			throw new DetailsAlreadyExistsException("Already Exists");
		} else {
			libraryRepository.save(library);
			status = true;
		}
		return status;
	}

	public boolean deleteLibraryDetails(String username, int bookId) throws DetailsNotFoundException {
		boolean status = false;
		Optional<Library> user = libraryRepository.findByUsernameAndBookId(username, bookId);
		if (user.isPresent()) {
			libraryRepository.delete(user.get());
			status = true;
		} else {
			throw new DetailsNotFoundException("Details Not Found");
		}
		return status;
	}

}
