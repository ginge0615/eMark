package com.emart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emart.entity.ManufacturEntity;
import com.emart.model.OptionModel;
import com.emart.repository.ManufacturRepository;

@Service
public class ManufacturServiceImpl implements ManufacturService {

	@Autowired
	private ManufacturRepository repository;
	
	/**
	 * Get all manufacturs
	 * @return List<OptionModel>
	 */
	@Override
	public List<OptionModel> getAllManufacturs() {
		List<ManufacturEntity> lstEntity = repository.findAll();
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}
		
		List<OptionModel> lstModel = new ArrayList<OptionModel>(lstEntity.size());
		
		lstEntity.stream().forEach(entity ->{
			OptionModel model = new OptionModel();
			model.setLabel(entity.getManufacturName());
			model.setValue(entity.getId().toString());
			lstModel.add(model);
		});
		
		return lstModel;
	}

}
