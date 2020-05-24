package com.emart.service;

import java.util.ArrayList;
import java.util.Date;
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
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemViewRepository itemViewRepository;
	
	@Autowired
	private PictureRepository pictureRepository;
	
	@Autowired
	private DescriptionRepository descriptionRepository;
	
	
	/**
	 * Search item
	 * @param context
	 * @return List<ItemModel>
	 */
	public List<ItemModel> search(String context) {
		List<ItemViewEntity> lstEntity = itemViewRepository.findBySearchContextLike("%" + context + "%");
		
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
		Optional<ItemViewEntity> optEntity = itemViewRepository.findById(id);
		
		if (optEntity.isPresent()) {
			model = new ItemDetailModel();
			
			ItemViewEntity entity = optEntity.get();
			BeanUtils.copyProperties(entity, model);
			model.setNumber(1);
			
			//Get pictures
			List<PictureEntity> lstPictures = pictureRepository.findByItemId(id);
			
			if (!CollectionUtils.isEmpty(lstPictures)) {
				String[] picturesArray = new String[lstPictures.size()];
				
				for (int i = 0; i < lstPictures.size(); i++) {
					picturesArray[i] = lstPictures.get(i).getPicturePath();
				}
				
				model.setPictures(picturesArray);
			}
			
			//Get descriptions
			List<DescriptionEntity> lstDescription = descriptionRepository.findByItemId(id);
			
			if (!CollectionUtils.isEmpty(lstDescription)) {
				String[] descriptionsArray = new String[lstDescription.size()];
				
				for (int i = 0; i < lstDescription.size(); i++) {
					descriptionsArray[i] = lstDescription.get(i).getDescription();
				}
				
				model.setDescriptions(descriptionsArray);
			}
		}
		
		return model;
	}
	

	/**
	 * Add item
	 * @param model
	 */
	@Override
	public void addItem(ItemDetailModel model) {
		if (model == null) {
			return;
		}
		
		//Insert into item table
		ItemEntity entity = new ItemEntity();
		entity.setSellId(Integer.parseInt(model.getSeller()));
		entity.setCategoryId(Integer.parseInt(model.getCategory()));
		entity.setSubcategoryId(Integer.parseInt(model.getSubcategory()));
		entity.setManufacturId(Integer.parseInt(model.getManufactur()));
		entity.setItemName(model.getItemName());
		entity.setPrice(model.getPrice());
		entity.setStockNumber(model.getStock());
		entity.setSalesVolume(0);
		entity.setCreateDatetime(new Date());
		
		entity = itemRepository.save(entity);
		
		//Get the new item's id and set to model
		model.setId(entity.getId());
		
		//Insert into item_picture table
		for (int i = 0; i < model.getPictures().length; i++) {
			PictureEntity pictureEntity = new PictureEntity();
			pictureEntity.setItemId(model.getId());
			pictureEntity.setSeq(i + 1);
			pictureEntity.setPicturePath(model.getPictures()[i]);
			pictureRepository.save(pictureEntity);
		}
		
		// Insert into item_description table
		for (int i = 0; i < model.getDescriptions().length; i++) {
			DescriptionEntity descEntity = new DescriptionEntity();
			descEntity.setItemId(model.getId());
			descEntity.setSeq(i + 1);
			descEntity.setDescription(model.getDescriptions()[i]);
			descriptionRepository.save(descEntity);
		}
	}

	/**
	 * Find all items by seller id.
	 * @param sellerId
	 * @return items
	 */
	@Override
	public List<ItemModel> findAllItems(Integer sellerId) {
		//Find all items from item view with sell id.
		List<ItemViewEntity> lstItemViewEntity = itemViewRepository.findBySellerId(sellerId);
		
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
			throw new BusinessException("E001", "item");
		}
		
		ItemEntity entity = optEntity.get();
		entity.setStockNumber(stock);
		itemRepository.save(entity);
		
	}

}
