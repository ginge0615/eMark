package com.emart.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.emart.entity.DiscountEntity;
import com.emart.entity.PurchaseHistoryEntity;
import com.emart.entity.TransactionEntity;
import com.emart.model.TransactionModel;
import com.emart.repository.DiscountRepository;
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
	
	@Autowired
	DiscountRepository discountRepository;
	
	@Autowired
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	@Autowired
	private TransactionDefinition transactionDefinition;
	
	
	/**
	 * Checkout
	 * @param models TransactionModel[]
	 * @return true:checkout sucessful, false:checkout failure
	 */
	@Transactional
	public boolean checkout(TransactionModel[] models) {
		Date datetime = new Date();
		
		TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
		
		for (TransactionModel model : models) {
			//Update stock if stock is larger than purchase number.
			int updatedCnt = itemRepository.updateStock(Integer.parseInt(model.getItemId()), model.getNumber());
			
			//If the inventory is insufficient, rollback
			if (updatedCnt == 0) {
				dataSourceTransactionManager.rollback(transactionStatus);
				return false;
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

		dataSourceTransactionManager.commit(transactionStatus);
		return true;
		
	}
	
	/**
	 * Get discount by code.
	 * @param code
	 * @return discount percent, if not found or expired then return null.
	 */
	public BigDecimal getDiscount(String code) {
		Optional<DiscountEntity> optEntity = discountRepository.findByDiscountCode(code);
		
		if (optEntity.isPresent()) {
			DiscountEntity entity = optEntity.get();
			
			Date now = Calendar.getInstance().getTime();
			
			//If within the validity period
			if (now.compareTo(entity.getStartDate()) >= 0 && now.compareTo(entity.getEndDate()) <= 0  ) {
				return entity.getPercentage();
			}
		}
		
		return null;
	}

}
