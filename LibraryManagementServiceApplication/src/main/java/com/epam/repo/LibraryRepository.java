package com.epam.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.Library;

public interface LibraryRepository extends CrudRepository<Library, Integer> {

	public Optional<Library> findByUsernameAndBookId(String username, int bookId);
	
	public boolean existsByBookId(int bookId);
}
