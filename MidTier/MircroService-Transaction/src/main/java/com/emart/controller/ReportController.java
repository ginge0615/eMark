package com.emart.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emart.model.ReportModel;
import com.emart.service.PurchaseHistoryService;

@RestController
@RequestMapping(value = "/report")
public class ReportController {
	@Autowired
	private PurchaseHistoryService service;
    
	/**
	 * Search reports
	 * @param sellId
	 * @param item
	 * @param fromDate
	 * @param toDate
	 * @return List<ReportModel>
	 */
	@GetMapping
    public ResponseEntity<List<ReportModel>> searchReports(
    		@RequestParam(value="sellId") Integer sellId,
    		@RequestParam(value="item") String item,
    		@RequestParam(value="fromDate") String fromDate, 
    		@RequestParam(value="toDate") String toDate) {
		
		try {
			List<ReportModel> lst = service.getReport(sellId, item, fromDate, toDate);
			
			if (CollectionUtils.isEmpty(lst)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			
			return ResponseEntity.ok(lst);
			
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}		
    }
	
}