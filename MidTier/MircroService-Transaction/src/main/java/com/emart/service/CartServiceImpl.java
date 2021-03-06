package com.emart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emart.entity.CartEntity;
import com.emart.entity.ItemViewEntity;
import com.emart.model.CartModel;
import com.emart.repository.CartRepository;
import com.emart.repository.ItemViewRepository;

@Service
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
			Optional<ItemViewEntity> optItemViewEntity = itemViewRepositor.findById(entity.getItemId());
			
			if (optItemViewEntity.isPresent()) {
				//Copy propeties from ItemViewEntity to cart model
				BeanUtils.copyProperties(optItemViewEntity.get(), model);
				model.setItemId(model.getId());
				//Copy propeties from cart entity to cart model
				BeanUtils.copyProperties(entity, model);
				
				lstModel.add(model);
			}
			
			
		});
		
		return lstModel;		
	}
	

	/**
	 * Add item to buyer's cart.
	 * @param buyerId
	 * @param itemId
	 * @param number
	 * @throws IllegalArgumentException
	 */
	@Override
	public void add(Integer buyerId, Integer itemId, Integer number) throws IllegalArgumentException {
		CartEntity entity = null;
		
		Optional<CartEntity> optEntity = cartRepositor.findByBuyerIdAndItemId(buyerId, itemId);
		
		if (optEntity.isPresent()) {
			//If there is the same item in cart, update number
			entity = optEntity.get();
			entity.setNumber(entity.getNumber() + number);
		} else {
			//If there is not the same item in cart, add to cart
			entity = new CartEntity();
			entity.setBuyerId(buyerId);
			entity.setItemId(itemId);
			entity.setNumber(number);
		}
		
		//Add to cart
		cartRepositor.save(entity);
	}

	/**
	 * Delete item from buyer's cart.
	 * @param cart id
	 * @exception IllegalArgumentException
	 */
	@Override
	public void delete(Integer id) {
		cartRepositor.deleteById(id);
	}

	/**
	 * Get items count in cart
	 * @param userId
	 * @return 
	 */
	public Integer getItemsCountInCart(Integer userId) {
		return cartRepositor.findByBuyerId(userId).size();
	}
}
