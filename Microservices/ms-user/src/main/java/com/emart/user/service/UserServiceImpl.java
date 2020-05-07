package com.emart.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emart.user.Const;
import com.emart.user.entity.BuyerEntity;
import com.emart.user.entity.SellerEntity;
import com.emart.user.repository.BuyerRepository;
import com.emart.user.repository.SellerRepository;
import com.emart.user.vo.UserModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BuyerRepository buyerRepositor;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	/**
	 * Login validate by username and password
	 * @param user UserModel
	 * @return true:login sucessful  false:login unsucessful
	 */
	@Override
	public boolean loginValid(UserModel user) {
		String password;
		
		if (Const.USER_TYPE_BUYER.equals(user.getRole())) {
			BuyerEntity buyer = buyerRepositor.findByUsername(user.getName());
			
			if (buyer == null) {
				return false;
			}
			
			password = buyer.getPassword();
			
		} else {
			SellerEntity seller = sellerRepository.findByUsername(user.getName());
			
			if (seller == null) {
				return false;
			}
			
			password = seller.getPassword();
		}
		
		return password.equals(user.getPassword());
	}

}
