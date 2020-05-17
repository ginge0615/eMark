package com.emart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
public class DiscountControllerTest {
	
	@Autowired
	private DiscountController uc;
	
	private MockMvc mvc; 
	 
	@Before
	public void setUp() throws Exception { 
		mvc = MockMvcBuilders.standaloneSetup(uc).build(); 
	}
	
	@Test
	public void testGetDiscount() throws Exception {
		RequestBuilder request = get("/discount/1000001").contentType(MediaType.APPLICATION_JSON);
				
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		
		String responseString = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(responseString.length() > 0);
	}
}
