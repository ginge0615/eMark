package com.emart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
    }
	
	/**
	 * Singin as buyer 
	 * @param buyer BuyerModel
	 * @return id, if failure then 0
	 */
	@PostMapping("/signinasbuyer")
	public ResponseEntity<Integer> signinAsBuyer(@RequestBody BuyerModel buyer) {
		Integer id = userService.signinAsBuyer(buyer);
		if (id > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(id);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(id);
	}
	
	/**
	 * Singin as seller 
	 * @param seller SellerModel
	 * @return id, if failure then 0
	 */
	@PostMapping("/signinasseller")
	public ResponseEntity<Integer> signinAsBuyer(@RequestBody SellerModel seller) {
		Integer id = userService.signinAsSeller(seller);
		if (id > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(id);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(id);
	}
}