package com.epam.exceptionhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.epam.dto.LibraryDto;
import com.epam.entity.Library;

import com.epam.exception.DetailsNotFoundException;

import com.epam.repo.LibraryRepository;
import com.epam.service.LibraryService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureMockMvc
@SpringBootTest
class RestExceptionHandlerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	LibraryService librrayService;
	
	@MockBean
	LibraryRepository libraryRepository;
	
	@MockBean
	LibraryDto libraryDto;
	Library library;
	
	protected <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException,JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	
	@BeforeEach
	void setUp() {
		libraryDto.setUsername("username");
		libraryDto.setBookId(123);
		library = new Library();
		library.setUsername("username");
		library.setBookId(123);
		
	}
	
	@Test
	void handlerDetailsNotFoundExceptionTest2() throws Exception {
		when(librrayService.delete(libraryDto.getUsername(),libraryDto.getBookId())).thenThrow(new DetailsNotFoundException("Details Not Found"));
		MvcResult result = mockMvc.perform(delete("/library/users/username/books/123"))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		HashMap<String ,String> data = this.mapFromJson(response, HashMap.class);
		assertEquals("Details Not Found",data.get("error"));
	}
	
//	@Test
//	void handlerBookAlreadyExistsExceptionTest() throws Exception {
////		Optional<Book> optionalBook = Optional.ofNullable(book);
////		when(bookRepository.findById(1)).thenReturn(optionalBook);
//		when(bookService.addBook(bookDto)).thenThrow(new BookAlreadyExistsException("Book Already Exists"));
//		MvcResult result = mockMvc.perform(post("/books/"))
//				.andExpect(status().isBadRequest()).andReturn();
//		String response = result.getResponse().getContentAsString();
//		HashMap<String ,String> data = this.mapFromJson(response, HashMap.class);
//		assertEquals("Book Already Exists",data.get("error"));
//	}
	
}
