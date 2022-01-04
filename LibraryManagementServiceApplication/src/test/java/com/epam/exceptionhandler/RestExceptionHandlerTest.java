package com.epam.exceptionhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import com.epam.entity.Library;
import com.epam.exception.DetailsAlreadyExistsException;
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
	LibraryService libraryService;

	@MockBean
	LibraryRepository libraryRepository;

	Library library;

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@BeforeEach
	void setUp() {
		library = new Library();
		library.setUsername("username");
		library.setBookId(123);

	}

	@Test
	void handlerDetailsNotFoundExceptionTest() throws Exception {
		when(libraryService.deleteLibraryDetails("username", 11))
				.thenThrow(new DetailsNotFoundException("Book Not Found"));
		MvcResult result = mockMvc.perform(delete("/library/users/username/books/11")).andExpect(status().isNotFound())
				.andReturn();
		String response = result.getResponse().getContentAsString();
		HashMap<String, String> data = this.mapFromJson(response, HashMap.class);
		assertEquals("Book Not Found", data.get("error"));
	}

	@Test
	void handlerDetailsAlreadyExistsExceptionTest() throws Exception {
		when(libraryService.addLibraryDetails(any())).thenThrow(new DetailsAlreadyExistsException("Book Not Found"));
		MvcResult result = mockMvc.perform(post("/library/users/username/books/11")).andExpect(status().isConflict())
				.andReturn();
		String response = result.getResponse().getContentAsString();
		HashMap<String, String> data = this.mapFromJson(response, HashMap.class);
		assertEquals("Book Not Found", data.get("error"));
	}

}
