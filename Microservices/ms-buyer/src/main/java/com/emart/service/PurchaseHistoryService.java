package com.emart.service;

import java.util.List;

import com.emart.model.PurchaseHistoryModel;

public interface PurchaseHistoryService {
	
	/**
	 * Get purchase history
	 * @param buyerId
	 * @return List<PurchaseHistoryModel>
	 */
	public List<PurchaseHistoryModel> getPurchaseHistory(String buyerId);
	
}
