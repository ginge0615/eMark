package com.emart.service;

import java.math.BigDecimal;

import com.emart.exception.BusinessException;
import com.emart.model.TransactionModel;

public interface TransactionService {
	
	/**
	 * Checkout
	 * @param models TransactionModel[]
	 * @throws BusinessException 
	 */
	public void checkout(TransactionModel[] models) throws BusinessException;
	
	/**
	 * Get discount by code.
	 * @param code
	 * @return discount percent, if not found or expired then return null.
	 */
	public BigDecimal getDiscount(String code);

}
