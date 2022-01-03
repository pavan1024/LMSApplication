package com.epam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Library")
@Getter
@Setter
public class Library {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LIBRARY_ID", updatable = false, nullable = false)
	int id;

	@Column(name = "USERNAME", unique = true)
	String username;

	@Column(name = "BOOK_ID", unique = true)
	int bookId;
}
