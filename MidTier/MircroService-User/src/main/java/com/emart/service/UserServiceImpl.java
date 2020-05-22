package com.emart.service;

import java.util.Calendar;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emart.Const;
import com.emart.entity.BuyerEntity;
import com.emart.entity.SellerEntity;
import com.emart.exception.BusinessException;
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
			Optional<BuyerEntity> buyer = buyerRepositor.findByUsername(user.getUsername());
			
			if (buyer.isPresent()) {
				userId = buyer.get().getId();
				password = buyer.get().getPassword();
			}
		} else {
			Optional<SellerEntity> seller = sellerRepository.findByUsername(user.getUsername());
			
			if (seller.isPresent()) {
				userId = seller.get().getId();
				password = seller.get().getPassword();
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
	 * @throws BusinessException
	 * @throws IllegalArgumentException
	 */
	@Override
	public void signinAsBuyer(BuyerModel buyer) throws BusinessException, IllegalArgumentException {
		BuyerEntity entity = new BuyerEntity();
		BeanUtils.copyProperties(buyer, entity);

		// If the buyer with same usename is exist, error
		if (buyerRepositor.findByUsername(buyer.getUsername()).isPresent()) {
			throw new BusinessException("E003","user name");
		}

		// If the buyer with same email is exist, error
		if (buyerRepositor.existsByEmail(buyer.getEmail())) {
			throw new BusinessException("E003", "email");
		}

		// If the buyer with same email is exist, error
		if (buyerRepositor.existsByMobilePhone(buyer.getMobilePhone())) {
			throw new BusinessException("E003", "mobile phone");
		}

		buyerRepositor.save(entity);

	}

	/**
	 * Singin as seller
	 * @param seller
	 * @throws BusinessException
	 * @throws IllegalArgumentException
	 */
	@Override
	public void signinAsSeller(SellerModel seller) throws BusinessException, IllegalArgumentException {
		SellerEntity entity = new SellerEntity();
		BeanUtils.copyProperties(seller, entity);

		// If the buyer with same usename is exist, error
		if (sellerRepository.findByUsername(seller.getUsername()).isPresent()) {
			throw new BusinessException("E003", "user name");
		}

		// If the buyer with same email is exist, error
		if (sellerRepository.existsByEmail(seller.getEmail())) {
			throw new BusinessException("E003", "email");
		}

		// If the buyer with same email is exist, error
		if (sellerRepository.existsByCompanyName(seller.getCompanyName())) {
			throw new BusinessException("E003", "company name");
		}

		sellerRepository.save(entity);
	}

}
