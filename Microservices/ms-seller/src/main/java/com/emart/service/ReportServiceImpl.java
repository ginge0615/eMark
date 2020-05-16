package com.emart.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.emart.entity.ReportViewEntity;
import com.emart.model.ReportModel;
import com.emart.repository.ReportViewRepository;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportViewRepository repository;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	/**
	 * Search report
	 * @param sellerId
	 * @param item
	 * @param strFromDate
	 * @param strToDate
	 * @return List<ReportModel>
	 */
	@Override
	public List<ReportModel> search(Integer sellerId, String item, String strFromDate, String strToDate) throws ParseException  {
		java.sql.Date fromDate = null;
		java.sql.Date toDate = null;
		
		if (!StringUtils.isEmpty(strFromDate)) {
			fromDate = new java.sql.Date(sdf.parse(strFromDate).getTime());
		}
		
		if (!StringUtils.isEmpty(strToDate)) {
			toDate = new java.sql.Date(sdf.parse(strToDate).getTime());
		}
		
		List<ReportViewEntity> lstEntity = repository.searchReport(sellerId, item, fromDate, toDate);
		
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
