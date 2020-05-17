package com.emart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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

import com.emart.model.ItemDetailModel;

import net.sf.json.JSONObject;



@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ItemControllerTest {
	
	@Autowired
	private ItemController uc;
	
	private MockMvc mvc; 
	 
	@Before
	public void setUp() throws Exception { 
		mvc = MockMvcBuilders.standaloneSetup(uc).build(); 
	}
	
	@Test
	public void testAdd() throws Exception {
		ItemDetailModel model = new ItemDetailModel();
		model.setCategory("1");
		model.setSubcategory("1");
		model.setManufactur("2");
		model.setItemName("A6S");
		model.setNumber(0);
		model.setPrice(new BigDecimal(100.2));
		model.setStock(500);
		model.setPictures(new String[] {"/test/pic1.jpg", "/test/pic2.jpg"});
		model.setDescriptions(new String[] {"desc1", "desc2", "desc3"});
		model.setSalesVolume(0);
		model.setSeller("1");
		
		RequestBuilder request = post("/item").contentType(MediaType.APPLICATION_JSON)
				.content(JSONObject.fromObject(model).toString());

		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		
		JSONObject jsonObject = JSONObject.fromObject(responseString);
		ItemDetailModel modelResult = (ItemDetailModel) JSONObject.toBean(jsonObject, ItemDetailModel.class);
		Assert.assertTrue(modelResult.getId() > 0);
	}
	
	
	@Test
	public void testSearch() throws Exception {
		String context = "A6";
		RequestBuilder request = get("/item/" + context).contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(responseString.length() > 0);
		System.out.println(responseString);
		
	}
	
	@Test
	public void testViewDetail() throws Exception {
		RequestBuilder request = get("/item/detail/1").contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(responseString.length() > 0);
		System.out.println(responseString);
		
	}
	
	@Test
	public void testGetAllItemsBySeller() throws Exception {
		RequestBuilder request = get("/item/all/1").contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(responseString.length() > 0);
		System.out.println(responseString);
		
	}
	
	@Test
	public void testUpdateStock() throws Exception {
		RequestBuilder request = put("/item/stock").contentType(MediaType.APPLICATION_JSON)
				.param("id", "1").param("stock", "100");
		
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(responseString.length() == 0);
		
	}
}
