package com.todo.hellouser.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.todo.hellouser.HelloUserApplicationTests;

public class HelloUserControllerTest extends HelloUserApplicationTests{

	@Test
	public void returnListOfEmailIFUsernameExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("https://jsonplaceholder.typicode.com/users?username=Samantha")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(jsonPath("$.emailList").exists());
	}
	
	@Test
	public void exteptionWhenUsernameIsNull() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("https://jsonplaceholder.typicode.com/users?username=")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(jsonPath("$.message").value("username should not be empty or null"));
	}
	
//	@Test
//	public void exteptionWhenValidEmailListEmpty() throws Exception {
//		
//		mockMvc.perform(MockMvcRequestBuilders.get("https://jsonplaceholder.typicode.com/users?username=Samantha")
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON)
//				)
//				.andExpect(MockMvcResultMatchers.status().is(400))
//				.andExpect(jsonPath("$.message").value("username should not be empty or null"));
//	}
	
	@Test
	public void exceptionWhenUsernameNotExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("https://jsonplaceholder.typicode.com/users?username=test")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().is(404))
				.andExpect(jsonPath("$.message").value("No user found with username: test"));
	}
	
	@Test
	public void exceptionWhenWrongAcceptType() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("https://jsonplaceholder.typicode.com/users?username=Samantha")
				.accept(MediaType.APPLICATION_XML)
				.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().is(406));
	}
}
