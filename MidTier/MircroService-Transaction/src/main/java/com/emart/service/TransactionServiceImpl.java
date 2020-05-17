package com.emart.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emart.entity.PurchaseHistoryEntity;
import com.emart.entity.TransactionEntity;
import com.emart.exception.BusinessException;
import com.emart.model.TransactionModel;
import com.emart.repository.ItemRepository;
import com.emart.repository.PurchaseHistoryRepository;
import com.emart.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	PurchaseHistoryRepository historyRepository;
	
	
	/**
	 * Checkout
	 * @param models TransactionModel[]
	 * @throws BusinessException 
	 */
	@Transactional
	public void checkout(TransactionModel[] models) throws BusinessException {
		Date datetime = new Date();
		
		for (TransactionModel model : models) {
			//Update stock if stock is larger than purchase number.
			int updatedCnt = itemRepository.updateStock(Integer.parseInt(model.getItemId()), model.getNumber());
			
			//If the inventory is insufficient, throw exception and rollback
			if (updatedCnt == 0) {
				throw new BusinessException("E003");
			}
			
			//Create transaction
			TransactionEntity entity = new TransactionEntity();
			BeanUtils.copyProperties(model, entity);
			entity.setTransactionDatetime(datetime);
			
			entity = transactionRepository.save(entity);
			
			//Create purchase history
			PurchaseHistoryEntity historyEntity = new PurchaseHistoryEntity();
			BeanUtils.copyProperties(model, historyEntity);
			historyEntity.setTransactionId(entity.getId());
			historyEntity.setDatetime(datetime);
			
			historyRepository.save(historyEntity);
			
		}
	}
}
