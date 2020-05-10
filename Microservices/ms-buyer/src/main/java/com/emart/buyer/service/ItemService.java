package com.emart.buyer.service;

import java.util.List;

import com.emart.buyer.model.ItemDetailModel;
import com.emart.buyer.model.ItemModel;

public interface ItemService {

	/**
	 * Search item
	 * @param context
	 * @return List<ItemModel>
	 */
	public List<ItemModel> search(String context);
	
	/**
	 * Get item detail
	 * @param id
	 * @return ItemDetailModel
	 */
	public ItemDetailModel getItemDetail(String id);
	
	/**
	 * Get purchase history
	 * @param userId
	 * @return List<ItemModel>
	 */
	public List<ItemModel> getPurchaseHistory(String userId);
	
}
