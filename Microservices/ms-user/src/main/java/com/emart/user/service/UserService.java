package com.emart.user.service;

import com.emart.user.model.BuyerModel;
import com.emart.user.model.SellerModel;
import com.emart.user.model.UserModel;

public interface UserService {
	
	/**
	 * Validate user by username and password
	 * @param user UserModel
	 * @return user id
	 */
	public int validateByUsernameAndPassword(UserModel user);
	
	/**
	 * Singin as buyer
	 * @param buyer
	 * @return true:sucessful  false:failure
	 */
	public boolean signinAsBuyer(BuyerModel buyer);
	
	/**
	 * Singin as seller
	 * @param seller
	 * @return true:sucessful  false:failure
	 */
	public boolean signinAsSeller(SellerModel seller);
}
