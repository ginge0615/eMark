package com.emart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.entity.ItemViewEntity;

@Repository
public interface ItemViewRepository extends JpaRepository<ItemViewEntity, Integer>{

	/**
	 * Get all items by seller id
	 * @param sellerId
	 * @return List<ItemViewEntity>
	 */
	public List<ItemViewEntity> findBySellerId(Integer sellerId);
	
	/**
	 * Search item
	 * @param searchContext
	 * @return List<ItemViewEntity>
	 */
	public List<ItemViewEntity> findBySearchContextLike(String searchContext);

}
