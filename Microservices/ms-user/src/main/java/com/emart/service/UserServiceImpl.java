package com.emart.service;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emart.Const;
import com.emart.entity.BuyerEntity;
import com.emart.entity.SellerEntity;
import com.emart.model.BuyerModel;
import com.emart.model.SellerModel;
import com.emart.model.UserModel;
import com.emart.repository.BuyerRepository;
import com.emart.repository.SellerRepository;

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
	public Integer validateByUsernameAndPassword(UserModel user) {
		Integer userId = 0;
		String password = "";
		
		if (Const.USER_TYPE_BUYER.equals(user.getRole())) {
			BuyerEntity buyer = buyerRepositor.findByUsername(user.getUsername());
			
			if (buyer != null) {
				userId = buyer.getId();
				password = buyer.getPassword();
			}
		} else {
			SellerEntity seller = sellerRepository.findByUsername(user.getUsername());
			
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
	 * @return id, if failure then 0
	 */
	@Override
	public Integer signinAsBuyer(BuyerModel buyer) {
		BuyerEntity entity = new BuyerEntity();
		BeanUtils.copyProperties(buyer, entity);
		
		try {
			entity = buyerRepositor.save(entity);
		} catch (Exception e) {
			log.error(e.toString());
			return 0;
		}
		
		return entity.getId();
	}

	/**
	 * Singin as seller
	 * @param seller
	 * @return id, if failure then 0
	 */
	@Override
	public Integer signinAsSeller(SellerModel seller) {
		SellerEntity entity = new SellerEntity();
		BeanUtils.copyProperties(seller, entity);
		entity.setCreateDatetime(Calendar.getInstance().getTime());
		
		try {
			entity = sellerRepository.save(entity);
		} catch (Exception e) {
			log.error(e.toString());
			return 0;
		}
		
		return entity.getId();
	}

}
