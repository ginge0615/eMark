package com.emart.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.emart.model.ReportModel;

public interface ReportService {

	/**
	 * Search report
	 * @param sellId
	 * @param item
	 * @param fromDate
	 * @param toDate
	 * @return List<ReportModel>
	 * @throws ParseException 
	 */
	public List<ReportModel> search(Integer sellId, String item, String fromDate, String toDate) throws ParseException;
}
