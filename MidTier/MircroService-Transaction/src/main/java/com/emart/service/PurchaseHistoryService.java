package com.emart.service;

import java.text.ParseException;
import java.util.List;

import com.emart.model.PurchaseHistoryModel;
import com.emart.model.ReportModel;

public interface PurchaseHistoryService {
	
	/**
	 * Get purchase history
	 * @param buyerId
	 * @return List<PurchaseHistoryModel>
	 */
	public List<PurchaseHistoryModel> getPurchaseHistory(Integer buyerId);
	
	/**
	 * Search report
	 * @param sellId
	 * @param item
	 * @param fromDate
	 * @param toDate
	 * @return List<ReportModel>
	 * @throws ParseException 
	 */
	public List<ReportModel> getReport(Integer sellId, String item, String fromDate, String toDate) throws ParseException;
	
}
