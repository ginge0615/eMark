package com.emart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.util.Assert;

import com.emart.model.UserModel;
import com.emart.util.JSONChange;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {
	
	@Autowired
	private UserController uc;
	
	private MockMvc mvc; 
	 
	@Before 
	public void setUp() throws Exception { 
		mvc = MockMvcBuilders.standaloneSetup(uc).build(); 
	}
	
	@Test
	public void testBuyerLogin() throws Exception {
		UserModel model = new UserModel();
		model.setUsername("buyer1");
		model.setPassword("123456");
		model.setRole("1");//buyer
		
		RequestBuilder request = post("/user/login").contentType(MediaType.APPLICATION_JSON).content(JSONChange.objToJson(model));
				
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		
		UserModel modelResult = JSONChange.jsonToObj(responseString, UserModel.class);
		Assert.hasLength(modelResult.getToken(), "Token is empty!");
	}
	
	@Test
	public void testSellerLogin() throws Exception {
		UserModel model = new UserModel();
		model.setUsername("seller1");
		model.setPassword("123456");
		model.setRole("2");//seller
		
		RequestBuilder request = post("/user/login").contentType(MediaType.APPLICATION_JSON).content(JSONChange.objToJson(model));
				
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		
		UserModel modelResult = JSONChange.jsonToObj(responseString, UserModel.class);
		Assert.hasLength(modelResult.getToken(), "Token is empty!");
	}

}
