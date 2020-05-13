package com.emart.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.emart.entity.ItemViewEntity;
import com.emart.entity.PurchaseHistoryEntity;
import com.emart.model.PurchaseHistoryModel;
import com.emart.repository.ItemViewRepository;
import com.emart.repository.PurchaseHistoryRepository;

public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {
	private static final Logger log = LoggerFactory.getLogger(PurchaseHistoryServiceImpl.class);

	@Autowired
	private PurchaseHistoryRepository historyRepository;
	@Autowired
	private ItemViewRepository itemViewRepositor;
	
	/**
	 * Get purchase history
	 * @param buyerId
	 * @return List<PurchaseHistoryModel>
	 */
	public List<PurchaseHistoryModel> getPurchaseHistory(String buyerId) {
		List<PurchaseHistoryEntity> lstEntity = historyRepository.findByBuyerId(buyerId);
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}
		
		List<PurchaseHistoryModel> lstModel = new ArrayList<PurchaseHistoryModel>(lstEntity.size());

		//Convert entity to model   
		lstEntity.stream().forEach(entity -> {
			PurchaseHistoryModel model = new PurchaseHistoryModel();
			
			//Get item from item view
			ItemViewEntity itemViewEntity =  itemViewRepositor.findById(entity.getItemId()).get();
			
			//Copy propeties from ItemViewEntity to cart model
			BeanUtils.copyProperties(itemViewEntity, model);
			//Copy propeties from cart entity to cart model
			BeanUtils.copyProperties(entity, model);
			
			lstModel.add(model);
		});
		
		return lstModel;	
	}


}
