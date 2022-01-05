package com.epam.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.epam.client.BookClient;
import com.epam.dto.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LibraryBookController.class)
class LibraryBookControllerTest {

	@MockBean
	BookClient bookClient;

	@InjectMocks
	LibraryBookController libraryBookController;

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	void getAllBooksTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/library/books/")).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

	@Test
	void getBookTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/library/books/1")).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

	@Test
	void deleteBookTest() throws Exception {
		MvcResult result = mockMvc.perform(delete("/library/books/1")).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

	@Test
	void addBookTest() throws Exception {
		BookDto bookDto = new BookDto();
		HttpEntity<BookDto> book = new HttpEntity<>(bookDto);
		MvcResult result = mockMvc
				.perform(post("/library/books/").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(book)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);

	}

	@Test
	void UpdateBookTest() throws Exception {
		BookDto bookDto = new BookDto();
		HttpEntity<BookDto> book = new HttpEntity<>(bookDto);
		MvcResult result = mockMvc
				.perform(put("/library/books/1").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(book)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

}
