package com.emart.buyer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.emart.buyer.entity.DescriptionEntity;
import com.emart.buyer.entity.ItemEntity;
import com.emart.buyer.entity.ItemViewEntity;
import com.emart.buyer.entity.PictureEntity;
import com.emart.buyer.model.ItemDetailModel;
import com.emart.buyer.model.ItemModel;
import com.emart.buyer.repository.DescriptionRepository;
import com.emart.buyer.repository.ItemRepository;
import com.emart.buyer.repository.ItemViewRepository;
import com.emart.buyer.repository.PictureRepository;

public class ItemServiceImpl implements ItemService {
	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemViewRepository itemViewRepositor;
	
	@Autowired
	private ItemRepository itemRepositor;
	
	@Autowired
	private PictureRepository pictureRepositor;
	
	@Autowired
	private DescriptionRepository descptionRepositor;
	
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
		lstEntity.stream().forEach(entity -> lstModel.add(toItemModel(entity)));
		
		return lstModel;
		
	}
	
	/**
	 * Convert entity to model   
	 * @param entity
	 * @return ItemModel
	 */
	private ItemModel toItemModel(ItemViewEntity entity) {
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
	public ItemDetailModel getItemDetail(Integer id){
		ItemDetailModel model = null;
		
		//Get item from Item table
		Optional<ItemEntity> optEntity = itemRepositor.findById(id);
		
		if (optEntity.isPresent()) {
			ItemEntity itemEntity = optEntity.get();
			
			//Get item info from Item view
			ItemViewEntity itemViewEntity = itemViewRepositor.findById(id).get();
			
			//Get pictures
			List<PictureEntity> lstPictures = pictureRepositor.findByItemId(id);
			
			//Get descriptions
			List<DescriptionEntity> lstescription = descptionRepositor.findByItemId(id);
			
			model = new ItemDetailModel();
			model.setId(String.valueOf(id));
			model.setCategory(String.valueOf(itemEntity.getCategoryId()));
			model.setSubcategory(String.valueOf(itemEntity.getSubcategoryId()));
			model.setManufactur(String.valueOf(itemEntity.getManufacturId()));
			model.setSellerId(String.valueOf(itemEntity.getSellId()));
			model.setSeller(itemViewEntity.getSeller());
			model.setVolume(itemEntity.getSalesVolume());
			model.setNum(1);
			model.setPictures((String[])lstPictures.toArray());
			model.setDescriptions((String[])lstescription.toArray());
		}
		
		return model;
	}
}
