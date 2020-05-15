package com.emart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.entity.SubcategoryEntity;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubcategoryEntity, Integer>{
	
	/**
	 * Get sub categories
	 * @param categoryId
	 * @return List<SubcategoryEntity>
	 */
	public List<SubcategoryEntity> findByCategoryId(Integer categoryId);
}
