package com.emart.service;

import com.emart.exception.BusinessException;
import com.emart.model.TransactionModel;

public interface TransactionService {
	
	/**
	 * Checkout
	 * @param models TransactionModel[]
	 * @throws BusinessException 
	 */
	public void checkout(TransactionModel[] models) throws BusinessException;

}
