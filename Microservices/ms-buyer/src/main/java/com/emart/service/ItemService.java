package com.emart.service;

import java.util.List;

import com.emart.model.ItemDetailModel;
import com.emart.model.ItemModel;

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
	public ItemDetailModel getItemDetail(Integer id);

}
