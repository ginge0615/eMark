package com.emart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.entity.PurchaseHistoryEntity;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistoryEntity, Integer>{

	public List<PurchaseHistoryEntity> findByBuyerId(Integer buyerId);
	
	public List<PurchaseHistoryEntity> findBySellerId(Integer sellerId);
}
