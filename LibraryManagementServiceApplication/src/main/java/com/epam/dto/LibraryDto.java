package com.epam.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class LibraryDto {
	
	int id;
	int bookId;
	String username;

}
