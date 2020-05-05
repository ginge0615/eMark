package com.emart.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emart.user.bean.User;
import com.emart.user.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User login(@RequestBody User user) {
    	System.out.println(user.getName() + " " + user.getPassword());
    	return user;
    }
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
    	return "hello";
    }
}