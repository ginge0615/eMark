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
import com.emart.buyer.entity.ItemViewEntity;
import com.emart.buyer.entity.PictureEntity;
import com.emart.buyer.model.ItemDetailModel;
import com.emart.buyer.model.ItemModel;
import com.emart.buyer.repository.DescriptionRepository;
import com.emart.buyer.repository.ItemViewRepository;
import com.emart.buyer.repository.PictureRepository;

public class ItemServiceImpl implements ItemService {
	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemViewRepository itemViewRepositor;
	
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
		lstEntity.stream().forEach(entity -> {
			ItemModel model = new ItemModel();
			BeanUtils.copyProperties(entity, model);
			lstModel.add(model);
		});
		
		return lstModel;
		
	}

	
	/**
	 * Get item detail
	 * @param id
	 * @return ItemDetailModel
	 */
	public ItemDetailModel getItemDetail(Integer id){
		ItemDetailModel model = null;
		
		//Get item from Item view
		Optional<ItemViewEntity> optEntity = itemViewRepositor.findById(id);
		
		if (optEntity.isPresent()) {
			model = new ItemDetailModel();
			
			ItemViewEntity entity = optEntity.get();			
			
			//Get pictures
			List<PictureEntity> lstPictures = pictureRepositor.findByItemId(id);
			
			//Get descriptions
			List<DescriptionEntity> lstescription = descptionRepositor.findByItemId(id);
			
			BeanUtils.copyProperties(entity, model);
			model.setNumber(1);
			model.setPictures((String[])lstPictures.toArray());
			model.setDescriptions((String[])lstescription.toArray());
		}
		
		return model;
	}
}
