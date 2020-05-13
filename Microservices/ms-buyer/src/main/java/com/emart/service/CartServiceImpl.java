package com.emart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.emart.entity.CartEntity;
import com.emart.entity.ItemViewEntity;
import com.emart.model.CartModel;
import com.emart.model.ItemDetailModel;
import com.emart.repository.CartRepository;
import com.emart.repository.ItemViewRepository;

public class CartServiceImpl implements CartService {
	private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	private CartRepository cartRepositor;
	
	@Autowired
	private ItemViewRepository itemViewRepositor;
	
	/**
	 * Get cart
	 * @param userId
	 * @return List<CartModel>
	 */
	@Override
	public List<CartModel> getCart(Integer userId) {
		List<CartEntity> lstEntity = cartRepositor.findByBuyerId(userId);
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			log.info("The cart is empty! user id=" + userId);
			return null;
		}
		
		List<CartModel> lstModel = new ArrayList<CartModel>(lstEntity.size());

		//Convert entity to model   
		lstEntity.stream().forEach(entity -> {
			CartModel model = new CartModel();
			
			//Get item from item view
			ItemViewEntity itemViewEntity = itemViewRepositor.findById(entity.getItemId()).get();
			
			//Copy propeties from ItemViewEntity to cart model
			BeanUtils.copyProperties(itemViewEntity, model);
			//Copy propeties from cart entity to cart model
			BeanUtils.copyProperties(entity, model);
			
			lstModel.add(model);
			
		});
		
		return lstModel;		
	}
	

	/**
	 * Add item to buyer's cart.
	 * @param model CartModel
	 * @return the number of items in buyer's cart
	 */
	@Override
	public Integer add(CartModel model) {
		CartEntity entity = new CartEntity();
		
		//Copy propeties from cart model to cart entity
		BeanUtils.copyProperties(model, entity);
		
		//Add to cart
		cartRepositor.save(entity);
		
		//Get the number of items in buyer's cart
		return cartRepositor.findByBuyerId(model.getBuyerId()).size();
	}

	/**
	 * Delete item from buyer's cart.
	 * @param model CartModel
	 * @return the number of items in buyer's cart
	 */
	@Override
	public Integer delete(CartModel model) {
		CartEntity entity = new CartEntity();
		
		//Copy propeties from cart model to cart entity
		BeanUtils.copyProperties(model, entity);
		
		//Delete item from buyer's cart
		cartRepositor.delete(entity);
		
		//Get the number of items in buyer's cart
		return cartRepositor.findByBuyerId(model.getBuyerId()).size();
	}

}
