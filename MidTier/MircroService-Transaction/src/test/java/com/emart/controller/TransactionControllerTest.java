package com.emart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.emart.model.TransactionModel;

import net.sf.json.JSONArray;



@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TransactionControllerTest {
	
	@Autowired
	private TransactionController uc;
	
	private MockMvc mvc; 
	 
	@Before
	public void setUp() throws Exception { 
		mvc = MockMvcBuilders.standaloneSetup(uc).build(); 
	}
	
	@Test
	public void testCheckout() throws Exception {
		TransactionModel[] models = new TransactionModel[2];
		
		models[0] = new TransactionModel();
		models[0].setBuyerId(1);
		models[0].setItemId(1);
		models[0].setPrice(new BigDecimal(200));
		models[0].setPurchaseNumber(5);
		models[0].setSellerId(1);
		models[0].setTransactionAmount(new BigDecimal(1000));
		models[0].setType("1");
		
		models[1] = new TransactionModel();
		models[1].setBuyerId(1);
		models[1].setItemId(2);
		models[1].setPrice(new BigDecimal(300));
		models[1].setPurchaseNumber(4);
		models[1].setSellerId(1);
		models[1].setTransactionAmount(new BigDecimal(1200));
		models[1].setType("1");
		
		RequestBuilder request = post("/transaction").contentType(MediaType.APPLICATION_JSON)
				.content(JSONArray.fromObject(models).toString());
				
		mvc.perform(request).andExpect(status().isOk());
	}

}
