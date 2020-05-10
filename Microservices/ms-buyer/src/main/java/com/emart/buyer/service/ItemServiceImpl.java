package com.emart.buyer.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.emart.buyer.entity.ItemViewEntity;
import com.emart.buyer.model.ItemDetailModel;
import com.emart.buyer.model.ItemModel;
import com.emart.buyer.repository.ItemViewRepository;

public class ItemServiceImpl implements ItemService {
	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemViewRepository itemViewRepositor;
	
	/**
	 * Search item
	 * @param context
	 * @return List<ItemModel>
	 */
	public List<ItemModel> search(String context) {
		List<ItemViewEntity> lstEntity = itemViewRepositor.findBySearchContextLike("%" + context + "%");
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}
		
		List<ItemModel> lstModel = new ArrayList<ItemModel>(lstEntity.size());

		//Convert entity to model   
		lstEntity.stream().forEach(entity -> lstModel.add(toModel(entity)));
		
		return lstModel;
		
	}
	
	/**
	 * Convert entity to model   
	 * @param entity
	 * @return ItemModel
	 */
	private ItemModel toModel(ItemViewEntity entity) {
		ItemModel model = new ItemModel();
		
		//Copy propeties from cart entity to cart model
		BeanUtils.copyProperties(entity, model);
		
		return model;
	}
	
	/**
	 * Get item detail
	 * @param id
	 * @return ItemDetailModel
	 */
	public ItemDetailModel getItemDetail(String id){
		return null;
	}
	
	/**
	 * Get purchase history
	 * @param userId
	 * @return List<ItemModel>
	 */
	public List<ItemModel> getPurchaseHistory(String userId){
		return null;
	}

}
