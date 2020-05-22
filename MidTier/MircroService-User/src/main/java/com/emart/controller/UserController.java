package com.emart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.exception.BusinessException;
import com.emart.model.BuyerModel;
import com.emart.model.SellerModel;
import com.emart.model.UserModel;
import com.emart.service.UserService;
import com.emart.util.JwtUtil;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource msgSource;
    
	/**
	 * Buyer or seller login by username and password. 
	 * @param user UserModel
	 * @return UserModel
	 */
	@PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody UserModel user) {
		//Validate user by username and password and get user id.
		Integer userId = userService.validateByUsernameAndPassword(user);
		if (userId > 0) {
			//If passed, generate Token.
			user.setId(userId);
			user.setToken(JwtUtil.generateToken(userId));
			return ResponseEntity.ok(user);
		} else {
			//Validate failure
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
    }
	
	/**
	 * Singin as buyer 
	 * @param buyer BuyerModel
	 * @return Message
	 */
	@PostMapping("/signinasbuyer")
	public ResponseEntity<String> signinAsBuyer(@RequestBody BuyerModel buyer) {
		
		try {
			userService.signinAsBuyer(buyer);
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(msgSource.getMessage(e.getMessageCode(), e.getArgs(), null));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(msgSource.getMessage("E500", null, null));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
		
	}
	
	/**
	 * Singin as seller 
	 * @param seller SellerModel
	 * @return Message
	 */
	@PostMapping("/signinasseller")
	public ResponseEntity<String> signinAsBuyer(@RequestBody SellerModel seller) {
		
		try {
			userService.signinAsSeller(seller);
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
}