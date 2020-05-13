package com.emart.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	@Query(value="update item set stock = stock - :num, sales_volume = sales_volume + :num where id = :id and stock >= :num")
	public int updateStock(@Param("id") Integer id, @Param("num") Integer number);
} 
