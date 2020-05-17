package com.emart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import com.emart.model.CartModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CartControllerTest {
	
	@Autowired
	private CartController uc;
	
	private MockMvc mvc; 
	 
	@Before
	public void setUp() throws Exception { 
		mvc = MockMvcBuilders.standaloneSetup(uc).build(); 
	}
	
	@Test
	public void testAdd() throws Exception {
		
		RequestBuilder request = post("/cart").contentType(MediaType.APPLICATION_JSON)
				.param("buyerId", "1")
				.param("itemId", "2")
				.param("number", "333");
				
		mvc.perform(request).andExpect(status().isOk());
	}
	
	@Test
	public void testGetCart() throws Exception {
		
		RequestBuilder request = get("/cart/1").contentType(MediaType.APPLICATION_JSON);
				
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		
		String responseString = mvcResult.getResponse().getContentAsString();
		
		JSONArray jsonArray = JSONArray.fromObject(responseString);
		Assert.assertTrue(jsonArray.size() > 0);
		System.out.println(responseString);
	}
	
	@Test
	public void testDelete() throws Exception {		
		RequestBuilder request = delete("/cart").contentType(MediaType.APPLICATION_JSON).param("id", "1");
		
		mvc.perform(request).andExpect(status().isOk());
	}

}
