package com.emart.service;

import java.util.List;

import com.emart.model.CartModel;

public interface CartService {
	/**
	 * Get cart
	 * @param userId
	 * @return List<CartModel>
	 */
	public List<CartModel> getCart(Integer userId);
	
	/**
	 * Add item to buyer's cart.
	 * @param model CartModel
	 * @return the number of items in buyer's cart
	 */
	public Integer add(CartModel model);
	
	/**
	 * Delete item from buyer's cart.
	 * @param model CartModel
	 * @return the number of items in buyer's cart
	 */
	public Integer delete(CartModel model);

}