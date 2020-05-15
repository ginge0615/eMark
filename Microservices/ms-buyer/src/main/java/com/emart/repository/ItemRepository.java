package com.emart.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emart.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer>{
	
	/**
	 * Update stock if stock is larger than purchase number.
	 * @param id
	 * @param number
	 * @return
	 */
	@Modifying
//	@Query(value="update item set stock = stock - ?2, sales_volume = sales_volume + ?2 where id = ?1 and stock >= ?2")
	@Transactional
	@Query(value="select count(*) from item",nativeQuery = true)
	public int updateStock(int id, int number);
} 
