package com.emart.user.service;

import com.emart.user.vo.UserModel;

public interface UserService {
	
	/**
	 * Login validate by username and password
	 * @param user UserModel
	 * @return true:login sucessful  false:login unsucessful
	 */
	public boolean loginValid(UserModel user);
	
	public boolean signinBuyer(UserModel user);
}
