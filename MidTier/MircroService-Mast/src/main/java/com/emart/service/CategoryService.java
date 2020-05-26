package com.emart.service;

import java.math.BigDecimal;
import java.util.List;

import com.emart.model.OptionModel;

public interface CategoryService {
	
	/**
	 * Get all categories
	 * @return List<OptionModel>
	 */
	public List<OptionModel> getAllCategories();
	
	/**
	 * Get sub categories
	 * @param categoryId
	 * @return List<OptionModel>
	 */
	public List<OptionModel> getSubCategories(String categoryId);
	
	/**
	 * Get tax by sub category
	 * @param categoryId
	 */
	public BigDecimal getTax(String categoryId);
}
