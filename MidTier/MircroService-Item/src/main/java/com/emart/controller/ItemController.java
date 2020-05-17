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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emart.exception.BusinessException;
import com.emart.model.ItemDetailModel;
import com.emart.model.ItemModel;
import com.emart.model.MessageModel;
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
	@GetMapping("/{context}")
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
	 * @return ItemDetailModel
	 */
	@PostMapping
    public ResponseEntity<ItemDetailModel> add(@RequestBody ItemDetailModel model) {
		ItemDetailModel rtnModel = null;
		
		try {
			rtnModel = service.addItem(model);
		} catch (BusinessException e) {
			log.error(e.toString());
			
			rtnModel = new ItemDetailModel();
			rtnModel.setMessageCode(e.getMessageCode());
			rtnModel.setArgs(e.getArgs());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rtnModel);
		}
		
		return ResponseEntity.ok(rtnModel);
    }
	
	/**
	 * Update stock
	 * @param ItemModel
	 * @return message
	 */
	@PutMapping("/stock")
	public ResponseEntity<MessageModel> updateStock(@RequestParam(value="id") Integer id, @RequestParam(value="stock") Integer stock) {
		try {
			service.updateStock(id, stock);
		} catch (BusinessException e) {
			log.error(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessageModel());
		}
		
		return ResponseEntity.ok(null);	
	}
	
}