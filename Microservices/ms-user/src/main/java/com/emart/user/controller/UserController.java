package com.emart.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emart.user.service.UserService;
import com.emart.user.vo.UserModel;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
    
	@PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody UserModel user) {
		
		boolean isOK = userService.loginValid(user);
		
		if (!isOK) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
		}
		
		//create token
		user.setToken("aaaa");
		return ResponseEntity.ok(user);
    }
	
	@PostMapping
	public ResponseEntity<UserModel> signin(@RequestBody UserModel user) {
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	

}