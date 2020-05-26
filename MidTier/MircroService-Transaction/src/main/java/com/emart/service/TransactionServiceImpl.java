package com.emart.service;

import java.util.Calendar;
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
		
		Date now = Calendar.getInstance().getTime();
		
		for (TransactionModel model : models) {
			//Update stock if stock is larger than purchase number.
			int updatedCnt = itemRepository.updateStock(model.getItemId(), model.getPurchaseNumber());
			
			//If the inventory is insufficient, throw exception and rollback
			if (updatedCnt == 0) {
				throw new BusinessException("E001");
			}
			
			//Create transaction
			TransactionEntity entity = new TransactionEntity();
			BeanUtils.copyProperties(model, entity);
			entity.setDatetime(now);
			
			entity = transactionRepository.save(entity);
			
			//Create purchase history
			PurchaseHistoryEntity historyEntity = new PurchaseHistoryEntity();
			BeanUtils.copyProperties(model, historyEntity);
			historyEntity.setTransactionId(entity.getId());
			historyEntity.setDatetime(now);
			
			historyRepository.save(historyEntity);
			
		}
	}
}
