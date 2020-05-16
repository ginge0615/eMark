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
	public Integer validateByUsernameAndPassword(UserModel user);
	
	/**
	 * Singin as buyer
	 * @param buyer
	 * @return id
	 */
	public Integer signinAsBuyer(BuyerModel buyer);
	
	/**
	 * Singin as seller
	 * @param seller
	 * @return id
	 */
	public Integer signinAsSeller(SellerModel seller);
}
