package com.emart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emart.entity.ReportViewEntity;
import com.emart.model.ReportModel;
import com.emart.repository.ReportViewRepository;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportViewRepository repository;

	/**
	 * Search report
	 * @param sellerId
	 * @param item
	 * @param fromDate
	 * @param toDate
	 * @return List<ReportModel>
	 */
	@Override
	public List<ReportModel> search(Integer sellerId, String item, Date fromDate, Date toDate) {
		List<ReportViewEntity> lstEntity = repository.searchReport(
				sellerId, item, new java.sql.Date(fromDate.getTime()), new java.sql.Date(toDate.getTime()));
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}
		
		List<ReportModel> lstModel = new ArrayList<ReportModel>(lstEntity.size());
		
		lstEntity.stream().forEach(entity -> {
			ReportModel model = new ReportModel();
			BeanUtils.copyProperties(entity, model);
			lstModel.add(model);
		});
		
		return lstModel;
	}

}
