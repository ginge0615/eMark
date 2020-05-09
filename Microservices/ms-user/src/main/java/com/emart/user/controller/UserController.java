package com.emart.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.user.model.BuyerModel;
import com.emart.user.model.SellerModel;
import com.emart.user.model.UserModel;
import com.emart.user.service.UserService;
import com.emart.user.util.JwtUtil;

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
		int userId = userService.validateByUsernameAndPassword(user);
		if (userId > 0) {
			//If passed, generate Token.
			user.setToken(JwtUtil.getInstance().generateToken(userId));
			return ResponseEntity.ok(user);
		} else {
			//Validate failure
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
		}
    }
	
	/**
	 * Singin as buyer 
	 * @param buyer BuyerModel
	 * @return BuyerModel
	 */
	@PostMapping("/signinasbuyer")
	public ResponseEntity<BuyerModel> signinAsBuyer(@RequestBody BuyerModel buyer) {
		if (userService.signinAsBuyer(buyer)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(buyer);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
	}
	
	/**
	 * Singin as seller 
	 * @param seller SellerModel
	 * @return SellerModel
	 */
	@PostMapping("/signinasseller")
	public ResponseEntity<SellerModel> signinAsBuyer(@RequestBody SellerModel seller) {
		if (userService.signinAsSeller(seller)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(seller);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
	}
	
	/**
	 * Validate token
	 * @param token string
	 * @return user id, if not passed return 0
	 */
	@GetMapping("/{token}")
	public ResponseEntity<Integer> validateToken(@PathVariable String token) {
		int userId = JwtUtil.getInstance().verifyToken(token);
		
		if (userId > 0) {
			return ResponseEntity.ok(userId);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userId);
		}
	}

}