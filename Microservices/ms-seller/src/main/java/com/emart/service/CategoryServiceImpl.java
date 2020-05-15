package com.emart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emart.entity.CategoryEntity;
import com.emart.entity.SubcategoryEntity;
import com.emart.model.OptionModel;
import com.emart.repository.CategoryRepository;
import com.emart.repository.SubcategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private SubcategoryRepository subResitory;

	/**
	 * Get all categories
	 * @return List<OptionModel>
	 */
	@Override
	public List<OptionModel> getAllCategories() {
		List<CategoryEntity> lstEntity = repository.findAll();
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}
		
		List<OptionModel> lstModel = new ArrayList<OptionModel>(lstEntity.size());
		
		lstEntity.stream().forEach(entity ->{
			OptionModel model = new OptionModel();
			model.setLabel(entity.getCategoryName());
			model.setValue(entity.getId().toString());
			lstModel.add(model);
		});
		
		return lstModel;
	}

	/**
	 * Get sub categories
	 * @param categoryId
	 * @return List<OptionModel>
	 */
	@Override
	public List<OptionModel> getSubCategories(String categoryId) {
		List<SubcategoryEntity> lstEntity = subResitory.findByCategoryId(Integer.parseInt(categoryId));
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}
		
		List<OptionModel> lstModel = new ArrayList<OptionModel>(lstEntity.size());
		
		lstEntity.stream().forEach(entity ->{
			OptionModel model = new OptionModel();
			model.setLabel(entity.getSubcategoryName());
			model.setValue(entity.getId().toString());
			lstModel.add(model);
		});
		
		return lstModel;
	}

}
