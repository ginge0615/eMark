package com.emart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

import com.emart.model.BuyerModel;
import com.emart.model.SellerModel;
import com.emart.model.UserModel;

import net.sf.json.JSONObject;


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
	public void testSigninAsBuyer() throws Exception {
		//Signin is sucessful
		BuyerModel model = new BuyerModel();
		model.setUsername("buyer3");
		model.setPassword("123456");
		model.setEmail("test@sina.com");
		model.setMobilePhone("13314285689");
		
		RequestBuilder request = post("/user/signinasbuyer").contentType(MediaType.APPLICATION_JSON).content(JSONObject.fromObject(model).toString());
				
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isCreated()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		Assert.assertNotEquals("0", responseString);
		
		//Repeat signin
		request = post("/user/signinasbuyer").contentType(MediaType.APPLICATION_JSON).content(JSONObject.fromObject(model).toString());
		
		mvcResult = mvc.perform(request).andExpect(status().isNotAcceptable()).andReturn();
		responseString = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals("0", responseString);
	}
	
	@Test
	public void testSigninAsSeller() throws Exception {
		//Signin is sucessful
		SellerModel model = new SellerModel();
		model.setUsername("seller3");
		model.setPassword("123456");
		model.setEmail("test@sina.com");
		model.setContactNumber("13314285689");
		model.setCompanyName("ibm");
		model.setBriefAboutCompany("test");
		model.setGstin("gstin");
		model.setPostalAddress("postaddress");
		model.setWebsite("http://www.emart.com");
		
		RequestBuilder request = post("/user/signinasseller").contentType(MediaType.APPLICATION_JSON).content(JSONObject.fromObject(model).toString());
				
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isCreated()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		Assert.assertNotEquals("0", responseString);
		
		//Repeat signin
		request = post("/user/signinasseller").contentType(MediaType.APPLICATION_JSON).content(JSONObject.fromObject(model).toString());
		
		mvcResult = mvc.perform(request).andExpect(status().isNotAcceptable()).andReturn();
		responseString = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals("0", responseString);
	}
	
	@Test
	public void testBuyerLogin() throws Exception {
		//Username and password is right.
		UserModel model = new UserModel();
		model.setUsername("buyer1");
		model.setPassword("123456");
		model.setRole("1");//buyer
		
		RequestBuilder request = post("/user/login").contentType(MediaType.APPLICATION_JSON).content(JSONObject.fromObject(model).toString());
				
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		
		JSONObject jsonObject = JSONObject.fromObject(responseString);
		UserModel modelResult= (UserModel)JSONObject.toBean(jsonObject, UserModel.class);
		Assert.assertTrue(modelResult.getToken().length() > 0);
		
		
		//Password is wrong.
		model.setPassword("1111");
		request = post("/user/login").contentType(MediaType.APPLICATION_JSON).content(JSONObject.fromObject(model).toString());
		
		mvc.perform(request).andExpect(status().isNotFound());
		
	}
	
	@Test
	public void testSellerLogin() throws Exception {
		//Username and password is right.
		UserModel model = new UserModel();
		model.setUsername("seller1");
		model.setPassword("123456");
		model.setRole("2");//seller
		
		RequestBuilder request = post("/user/login").contentType(MediaType.APPLICATION_JSON).content(JSONObject.fromObject(model).toString());
				
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		
		JSONObject jsonObject = JSONObject.fromObject(responseString);
		UserModel modelResult = (UserModel) JSONObject.toBean(jsonObject, UserModel.class);
		Assert.assertNotNull(modelResult.getToken());

		// Password is wrong.
		model.setPassword("1111");
		request = post("/user/login").contentType(MediaType.APPLICATION_JSON)
				.content(JSONObject.fromObject(model).toString());

		mvc.perform(request).andExpect(status().isNotFound());
	}
	
	@Test
	public void testValidateToken() throws Exception {
		// Username and password is right.
		UserModel model = new UserModel();
		model.setUsername("buyer1");
		model.setPassword("123456");
		model.setRole("1");// buyer

		RequestBuilder request = post("/user/login").contentType(MediaType.APPLICATION_JSON)
				.content(JSONObject.fromObject(model).toString());

		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();

		JSONObject jsonObject = JSONObject.fromObject(responseString);
		UserModel modelResult = (UserModel) JSONObject.toBean(jsonObject, UserModel.class);
		
		RequestBuilder request2 = get("/user/" + modelResult.getToken()).contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult2 = mvc.perform(request2).andExpect(status().isOk()).andReturn();
		String responseString2 = mvcResult2.getResponse().getContentAsString();
		Assert.assertTrue(responseString2.length() > 0);
	}

}
