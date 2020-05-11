package com.emart.buyer.service;

import java.util.List;

import com.emart.buyer.model.PurchaseHistoryModel;

public interface PurchaseHistoryService {
	
	/**
	 * Get purchase history
	 * @param buyerId
	 * @return List<PurchaseHistoryModel>
	 */
	public List<PurchaseHistoryModel> getPurchaseHistory(String buyerId);
	
}
