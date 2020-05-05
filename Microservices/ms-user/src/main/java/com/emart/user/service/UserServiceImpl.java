package com.emart.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emart.user.Const;
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
	 * @param username
	 * @param password
	 * @param userType ('1':buyer '2':seller)
	 * @return if passed return the id of user, otherwise return -1
	 */
	@Override
	public int loginValid(String username, String password, String userType) {
		int id = -1;
		if (Const.USER_TYPE_BUYER.equals(userType)) {
			Buyer buyer = buyerMapper.selectIdByNamePwd(username, password);
			id = buyer == null ? id : buyer.getId();
		} else {
			Seller seller = sellerMapper.selectIdByNamePwd(username, password);
			id = seller == null ? id : seller.getId();
		}
		
		return id;
	}

}
