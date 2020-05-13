package com.emart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.model.PurchaseHistoryModel;
import com.emart.service.PurchaseHistoryService;

@RestController
@RequestMapping(value = "/history")
public class PurchaseHistoryController {
	@Autowired
	private PurchaseHistoryService service;
	/**
	 * Get purchase history
	 * @param userId
	 * @return List<PurchaseHistoryModel>
	 */
	@GetMapping("/{userId}")
    public ResponseEntity<List<PurchaseHistoryModel>> getPurchaseHistory(@PathVariable String userId) {
		List<PurchaseHistoryModel> lst = service.getPurchaseHistory(userId);
		
		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(lst);
    }
}