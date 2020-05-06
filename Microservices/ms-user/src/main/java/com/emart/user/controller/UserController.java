package com.emart.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emart.user.bean.User;
import com.emart.user.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> login(@RequestBody User user) {
    	user.setToken("111");
    	return ResponseEntity.ok(user);
//    	return ResponseEntity.status(404).body(null);
//    	return ResponseEntity.status(301).body(user);
    }
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
    	return "hello";
    }
}