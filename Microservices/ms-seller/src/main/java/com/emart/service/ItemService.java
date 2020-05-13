package com.emart.service;

import java.util.List;

import com.emart.exception.BusinessException;
import com.emart.model.ItemDetailModel;
import com.emart.model.ItemModel;

public interface ItemService {

	/**
	 * Add item
	 * @param model
	 * @return added item mode
	 * @throws BusinessException
	 */
	public ItemDetailModel addItem(ItemDetailModel model) throws BusinessException;
	
	/**
	 * Find all items by seller id.
	 * @param sellId
	 * @return items
	 */
	public List<ItemModel> findAllItems(Integer sellId);
	
	/**
	 * Update stock
	 * @param itemId
	 * @param stock
	 * @return true:sucessful  false:failue
	 * @throws BusinessException
	 */
	public void updateStock(Integer itemId, int stock) throws BusinessException;

}
