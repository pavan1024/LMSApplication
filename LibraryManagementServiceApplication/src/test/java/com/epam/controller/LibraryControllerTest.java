package com.epam.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.dto.LibraryDto;
import com.epam.service.LibraryService;

@WebMvcTest(LibraryController.class)
class LibraryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	LibraryService libraryService;
	
	LibraryDto libraryDto = new LibraryDto();

	@Test
	void addLibraryDetailsTest() throws Exception {
		when(libraryService.addLibraryDetails(libraryDto)).thenReturn(libraryDto);
		MvcResult result = mockMvc.perform(post("/library/users/username/books/11")).andExpect(status().isCreated())
				.andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

	@Test
	void deleteLibraryDetailsTest() throws Exception {
		when(libraryService.deleteLibraryDetails("username", 11)).thenReturn("Library Details Deleted Successfully");
		MvcResult result = mockMvc.perform(delete("/library/users/username/books/11")).andExpect(status().isNoContent())
				.andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Library Details Deleted Successfully", response);

	}

	@Test
	void deleteLibraryDetailsErrorTest() throws Exception {
		when(libraryService.deleteLibraryDetails("username", 11)).thenReturn("");
		MvcResult result = mockMvc.perform(delete("/library/users/username/books/11")).andExpect(status().isNoContent())
				.andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);

	}

}
