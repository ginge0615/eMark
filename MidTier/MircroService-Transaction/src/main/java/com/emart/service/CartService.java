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
	 * @param buyerId
	 * @param itemId
	 * @param number
	 * @throws IllegalArgumentException
	 */
	public void add(Integer buyerId, Integer itemId, Integer number) throws IllegalArgumentException;
	
	/**
	 * Delete item from buyer's cart.
	 * @param cart id
	 * @exception IllegalArgumentException
	 */
	public void delete(Integer id) throws IllegalArgumentException;

}
