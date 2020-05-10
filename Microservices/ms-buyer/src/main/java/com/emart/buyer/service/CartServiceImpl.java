package com.emart.buyer.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.emart.buyer.entity.CartEntity;
import com.emart.buyer.model.CartModel;
import com.emart.buyer.model.ItemDetailModel;
import com.emart.buyer.repository.CartRepository;

public class CartServiceImpl implements CartService {
	private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	private CartRepository cartRepositor;
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * Get cart
	 * @param userId
	 * @return List<CartModel>
	 */
	@Override
	public List<CartModel> getCart(String userId) {
		List<CartEntity> lstEntity = cartRepositor.findByBuyerId(userId);
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			log.info("The cart is empty! user id=" + userId);
			return null;
		}
		
		List<CartModel> lstModel = new ArrayList<CartModel>(lstEntity.size());

		//Convert entity to model   
		lstEntity.stream().forEach(entity -> lstModel.add(toModel(entity)));
		
		return lstModel;		
	}
	
	/**
	 * Convert entity to model   
	 * @param entity
	 * @return CartModel
	 */
	private CartModel toModel(CartEntity entity) {
		CartModel model = new CartModel();
		
		//Copy propeties from cart entity to cart model
		BeanUtils.copyProperties(entity, model);
		
		//Get item detail by item id
		ItemDetailModel itemDetail = itemService.getItemDetail(model.getItemId());
		
		if (itemDetail != null) {
			//Copy propeties from item detail model to cart model
			BeanUtils.copyProperties(itemDetail, model);

			//Set cover photo with first picture
			if (itemDetail.getPictures().length > 0) {
				model.setPicture(itemDetail.getPictures()[0]);
			}
		} else {
			log.error("Item detail not found! Item id=" + model.getItemId() );
		}
		
		return model;
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
