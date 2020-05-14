package com.emart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emart.entity.DescriptionEntity;
import com.emart.entity.ItemEntity;
import com.emart.entity.ItemViewEntity;
import com.emart.entity.PictureEntity;
import com.emart.exception.BusinessException;
import com.emart.model.ItemDetailModel;
import com.emart.model.ItemModel;
import com.emart.repository.DescriptionRepository;
import com.emart.repository.ItemRepository;
import com.emart.repository.ItemViewRepository;
import com.emart.repository.PictureRepository;

@Service
public abstract class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemViewRepository itemViewRepository;
	
	
	@Autowired
	private PictureRepository pictureRepository;
	
	@Autowired
	private DescriptionRepository descriptionRepository;
	

	/**
	 * Add item
	 * @param model
	 * @return added item mode
	 */
	@Override
	public ItemDetailModel addItem(ItemDetailModel model) {
		if (model == null) {
			return null;
		}
		
		//Insert into item table
		ItemEntity entity = new ItemEntity();
		entity.setSellId(Integer.parseInt(model.getSeller()));
		entity.setCategoryId(Integer.parseInt(model.getCategory()));
		entity.setSubcategoryId(Integer.parseInt(model.getSubcategory()));
		entity.setManufacturId(Integer.parseInt(model.getManufactur()));
		entity.setItemName(model.getItem());
		entity.setPrice(model.getPrice());
		entity.setStockNumber(model.getStock());
		entity.setSalesVolume(0);
		
		entity = itemRepository.save(entity);
		
		//Get the new item's id and set to model
		model.setId(entity.getId());
		
		//Insert into item_picture table
		for (int i = 0; i < model.getPictures().length - 1; i++) {
			PictureEntity pictureEntity = new PictureEntity();
			pictureEntity.setItemId(model.getId());
			pictureEntity.setSeq(i + 1);
			pictureEntity.setPicturePath(model.getPictures()[i]);
			pictureRepository.save(pictureEntity);
		}
		
		// Insert into item_description table
		for (int i = 0; i < model.getDescriptions().length - 1; i++) {
			DescriptionEntity descEntity = new DescriptionEntity();
			descEntity.setItemId(model.getId());
			descEntity.setSeq(i + 1);
			descEntity.setDescription(model.getDescriptions()[i]);
			descriptionRepository.save(descEntity);
		}

		return model;
	}

	/**
	 * Find all items by seller id.
	 * @param sellId
	 * @return items
	 */
	@Override
	public List<ItemModel> findAllItems(Integer sellId) {
		//Find all items from item view with sell id.
		List<ItemViewEntity> lstItemViewEntity = itemViewRepository.findBySellId(sellId);
		
		if (CollectionUtils.isEmpty(lstItemViewEntity)) {
			return null;
		}
		
		List<ItemModel> lstModel = new ArrayList<ItemModel>(lstItemViewEntity.size());
		
		lstItemViewEntity.stream().forEach(entity -> {
			ItemModel model = new ItemModel();
			BeanUtils.copyProperties(entity, model);
			lstModel.add(model);
		});
		
		return lstModel;
	}
	
	/**
	 * Update stock
	 * @param itemId
	 * @param stock
	 * @return true:sucessful  false:failue
	 * @throws BusinessException
	 */
	@Override
	public void updateStock(Integer itemId, int stock) throws BusinessException {
		Optional<ItemEntity> optEntity = itemRepository.findById(itemId);
		
		if (!optEntity.isPresent()) {
			//Could not find the item
			throw new BusinessException("E001");
		}
		
		ItemEntity entity = optEntity.get();
		
		//If sales volume is lager then stock, error
		if (entity.getSalesVolume() > stock) {
			throw new BusinessException("E002");
		}
		
		entity.setStockNumber(stock);
		itemRepository.save(entity);
		
	}

}
