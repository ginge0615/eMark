package com.emart.user.service;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emart.user.Const;
import com.emart.user.entity.BuyerEntity;
import com.emart.user.entity.SellerEntity;
import com.emart.user.model.BuyerModel;
import com.emart.user.model.SellerModel;
import com.emart.user.model.UserModel;
import com.emart.user.repository.BuyerRepository;
import com.emart.user.repository.SellerRepository;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private BuyerRepository buyerRepositor;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	/**
	 * Validate by username and password
	 * @param user UserModel
	 * @return user id, return 0 if user can not found or password is incorrect.
	 */
	@Override
	public int validateByUsernameAndPassword(UserModel user) {
		int userId = 0;
		String password = "";
		
		if (Const.USER_TYPE_BUYER.equals(user.getRole())) {
			BuyerEntity buyer = buyerRepositor.findByUsername(user.getName());
			
			if (buyer != null) {
				userId = buyer.getId();
				password = buyer.getPassword();
			}
		} else {
			SellerEntity seller = sellerRepository.findByUsername(user.getName());
			
			if (seller != null) {
				userId = seller.getId();
				password = seller.getPassword();
			}
		}
		
		if (!password.equals(user.getPassword())) {
			userId = 0;
		}
		
		return userId;
	}

	/**
	 * Singin as buyer
	 * @param buyer BuyerModel
	 * @return BuyerEntity
	 */
	@Override
	public boolean signinAsBuyer(BuyerModel buyer) {
		BuyerEntity entity = new BuyerEntity();
		BeanUtils.copyProperties(buyer, entity);
		entity.setCreateDatetime(Calendar.getInstance().getTime());
		
		try {
			buyerRepositor.save(entity);
		} catch (Exception e) {
			log.error(e.toString());
			return false;
		}
		
		return true;
	}

	/**
	 * Singin as seller
	 * @param seller
	 * @return true:sucessful  false:failure
	 */
	@Override
	public boolean signinAsSeller(SellerModel seller) {
		SellerEntity entity = new SellerEntity();
		BeanUtils.copyProperties(seller, entity);
		entity.setCreateDatetime(Calendar.getInstance().getTime());
		
		try {
			sellerRepository.save(entity);
		} catch (Exception e) {
			log.error(e.toString());
			return false;
		}
		
		return true;
	}

}
