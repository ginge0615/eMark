package com.emart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.exception.BusinessException;
import com.emart.model.ItemModel;
import com.emart.model.MessageModel;
import com.emart.service.ItemService;

@RestController
@RequestMapping(value = "/stock")
public class StockController {
	private static final Logger log = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	private ItemService service;
    
	/**
	 * Find all items by seller id.
	 * @param sellId
	 * @return List<ItemModel>
	 */
	@GetMapping("/{sellId}")
    public ResponseEntity<List<ItemModel>> findAllItems(@PathVariable Integer sellId) {
		List<ItemModel> lstModel = service.findAllItems(sellId);
		
		if (lstModel == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(lstModel);
    }
	
	/**
	 * Update stock
	 * @param model
	 * @return message code
	 */
	@PutMapping
	public ResponseEntity<MessageModel> update(@RequestBody ItemModel model) {
		try {
			service.updateStock(model.getId(), model.getStock());
		} catch (BusinessException e) {
			log.error(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessageModel());
		}
		
		return ResponseEntity.ok(null);	
	}
	
}