package com.emart.service;

import com.emart.model.BuyerModel;
import com.emart.model.SellerModel;
import com.emart.model.UserModel;

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
