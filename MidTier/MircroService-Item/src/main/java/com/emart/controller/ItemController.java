package com.emart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.exception.BusinessException;
import com.emart.model.ItemDetailModel;
import com.emart.model.ItemModel;
import com.emart.model.MessageModel;
import com.emart.model.StockModel;
import com.emart.service.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
	private static final Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService service;
	
	/**
	 * Search items
	 * @param context
	 * @return List<ItemModel>
	 */
	@GetMapping()
    public ResponseEntity<List<ItemModel>> getAllItems() {
		return ResponseEntity.ok(service.getAllItems());
    }
	
	/**
	 * Search items
	 * @param context
	 * @return List<ItemModel>
	 */
	@GetMapping("/search/{context}")
    public ResponseEntity<List<ItemModel>> search(@PathVariable String context) {
		return ResponseEntity.ok(service.search(context));
    }
	
	/**
	 * View item detail
	 * @param itemId
	 * @return ItemDetailModel
	 */
	@GetMapping("/detail/{id}")
    public ResponseEntity<ItemDetailModel> viewDetail(@PathVariable String id) {
		return ResponseEntity.ok(service.getItemDetail(Integer.parseInt(id)));
    }
	
	
	/**
	 * Find all items by seller id.
	 * @param sellerId
	 * @return List<ItemModel>
	 */
	@GetMapping("/all/{sellerId}")
    public ResponseEntity<List<ItemModel>> getAllItemsBySeller(@PathVariable Integer sellerId) {
		return ResponseEntity.ok(service.findAllItems(sellerId));
    }
    
	/**
	 * Add item
	 * @param ItemDetailModel
	 * @return MessageModel
	 */
	@PostMapping
    public ResponseEntity<MessageModel> add(@RequestBody ItemDetailModel model) {
		try {
			service.addItem(model);
		} catch (BusinessException e) {
			log.error(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
				
    }
	
	/**
	 * Update stock
	 * @param StockModel
	 * @return message
	 */
	@PutMapping("/stock")
	public ResponseEntity<MessageModel> updateStock(@RequestBody StockModel model) {
		try {
			service.updateStock(model.getId(), model.getStock());
		} catch (BusinessException e) {
			log.error(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		return ResponseEntity.ok(null);	
	}
	
}