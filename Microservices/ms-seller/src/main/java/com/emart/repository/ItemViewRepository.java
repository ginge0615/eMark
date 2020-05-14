package com.emart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.entity.ItemViewEntity;

@Repository
public interface ItemViewRepository extends JpaRepository<ItemViewEntity, Integer>{

	/**
	 * Get all items by sell id
	 * @param sellId
	 * @return items
	 */
	public List<ItemViewEntity> findBySellId(Integer sellId);

}