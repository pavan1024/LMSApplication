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

import com.epam.client.UserClient;
import com.epam.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LibraryUserController.class)
class LibraryUserControllerTest {

	@MockBean
	private UserClient userClient;

	@InjectMocks
	LibraryUserController libraryUserController;

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	void getAllUsersTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/library/users/")).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

	@Test
	void getUserTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/library/users/username")).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

	@Test
	void deleteUserTest() throws Exception {
		MvcResult result = mockMvc.perform(delete("/library/users/username")).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

	@Test
	void addUserTest() throws Exception {
		UserDto userDto = new UserDto();
		HttpEntity<UserDto> book = new HttpEntity<>(userDto);
		MvcResult result = mockMvc
				.perform(post("/library/users/").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(book)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);

	}

	@Test
	void UpdateUserTest() throws Exception {
		UserDto userDto = new UserDto();
		HttpEntity<UserDto> user = new HttpEntity<>(userDto);
		MvcResult result = mockMvc
				.perform(put("/library/users/username").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(user)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

}
