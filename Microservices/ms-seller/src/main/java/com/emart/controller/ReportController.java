package com.emart.controller;

import java.util.Date;
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
import com.emart.service.ReportService;

@RestController
@RequestMapping(value = "/report")
public class ReportController {
	@Autowired
	private ReportService service;
    
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
    		@RequestParam(value="fromDate") Date fromDate, 
    		@RequestParam(value="toDate") Date toDate) {
		
		List<ReportModel> lst = service.search(sellId, item, fromDate, toDate);
		
		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(lst);
    }
	
}