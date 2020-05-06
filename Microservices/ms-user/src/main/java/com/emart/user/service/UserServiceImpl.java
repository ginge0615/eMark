package com.emart.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emart.user.Const;
import com.emart.user.bean.User;
import com.emart.user.dao.BuyerMapper;
import com.emart.user.dao.SellerMapper;
import com.emart.user.entity.Buyer;
import com.emart.user.entity.Seller;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BuyerMapper buyerMapper;
	
	@Autowired
	private SellerMapper sellerMapper;
	
	/**
	 * Logon validate by username and password
	 * @param user
	 * @return token string
	 */
	@Override
	public String loginValid(User user) {
		String token = "";
		String password = "";
		
		if (Const.USER_TYPE_BUYER.equals(user.getRole())) {
			Buyer buyer = buyerMapper.selectByName(user.getName());
			
			if (buyer != null) {
				password = buyer.getPassword();
			}
		} else {
			Seller seller = sellerMapper.selectByName(user.getName());
			
			if (seller != null) {
				password = seller.getPassword();
			}
		}
		
		//create token
		if (password.equals(user.getPassword())) {
			
		}
		
		return token;
	}

}
