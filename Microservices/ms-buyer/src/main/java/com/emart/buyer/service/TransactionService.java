package com.emart.buyer.service;

import java.math.BigDecimal;

import com.emart.buyer.model.TransactionModel;

public interface TransactionService {
	
	/**
	 * Checkout
	 * @param models TransactionModel[]
	 * @return true:checkout sucessful, false:checkout failure
	 */
	public boolean checkout(TransactionModel[] models);
	
	/**
	 * Get discount by code.
	 * @param code
	 * @return discount percent, if not found or expired then return null.
	 */
	public BigDecimal getDiscount(String code);

}
