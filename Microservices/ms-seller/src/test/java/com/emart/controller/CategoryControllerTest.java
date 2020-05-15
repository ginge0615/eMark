package com.emart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CategoryControllerTest {
	
	@Autowired
	private CategoryController uc;
	
	private MockMvc mvc; 
	 
	@BeforeEach
	public void setUp() throws Exception { 
		mvc = MockMvcBuilders.standaloneSetup(uc).build(); 
	}
	
	@Test
	public void testGetAllCategories() throws Exception {
		RequestBuilder request = get("/category").contentType(MediaType.APPLICATION_JSON);
				
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		
		System.out.println(responseString);
		
//		UserModel modelResult = JSONChange.jsonToObj(responseString, UserModel.class);
//		Assert.hasLength(modelResult.getToken(), "Token is empty!");
	}
	
	@Test
	public void testGetSubCategories() throws Exception {
		RequestBuilder request = get("/category/1").contentType(MediaType.APPLICATION_JSON);
				
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		
		System.out.println(responseString);
		
//		UserModel modelResult = JSONChange.jsonToObj(responseString, UserModel.class);
//		Assert.hasLength(modelResult.getToken(), "Token is empty!");
	}

}
