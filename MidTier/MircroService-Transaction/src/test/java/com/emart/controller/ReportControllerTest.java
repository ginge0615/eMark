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

import net.sf.json.JSONArray;



@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ReportControllerTest {
	
	@Autowired
	private ReportController uc;
	
	private MockMvc mvc; 
	 
	@Before
	public void setUp() throws Exception { 
		mvc = MockMvcBuilders.standaloneSetup(uc).build(); 
	}
	
	@Test
	public void testSearchReports() throws Exception {
		
//		RequestBuilder request = get("/report").contentType(MediaType.TEXT_PLAIN)
//				.param("sellId", "1")
//				.param("item", "A6");
//				
//		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
//		
//		String responseString = mvcResult.getResponse().getContentAsString();
//		
//		JSONArray jsonArray = JSONArray.fromObject(responseString);
//		Assert.assertTrue(jsonArray.size() > 0);
	}

}
