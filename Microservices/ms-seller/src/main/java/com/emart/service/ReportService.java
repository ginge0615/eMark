package com.emart.service;

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
	 */
	public List<ReportModel> search(Integer sellId, String item, Date fromDate, Date toDate);
}
