package com.epam.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.Library;

public interface LibraryRepository extends CrudRepository<Library, Integer> {
	public Optional<Library> findByUsername(String username);

	public Optional<Library> findByBookId(int bookId);

	public Optional<Library> findByUsernameAndBookId(String username, int bookId);
}
