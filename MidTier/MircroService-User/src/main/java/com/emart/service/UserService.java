package com.emart.service;

import com.emart.exception.BusinessException;
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
	 * @param buyer BuyerModel
	 * @throws BusinessException
	 * @throws IllegalArgumentException
	 * 
	 */
	public void signinAsBuyer(BuyerModel buyer) throws BusinessException, IllegalArgumentException;
	
	/**
	 * Singin as seller
	 * @param seller
	 * @throws BusinessException
	 * @throws IllegalArgumentException
	 */
	public void signinAsSeller(SellerModel seller) throws BusinessException, IllegalArgumentException;
}
