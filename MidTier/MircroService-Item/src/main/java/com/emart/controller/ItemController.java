package com.emart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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
	@GetMapping("/search/{context}")
    public ResponseEntity<List<ItemModel>> search(@PathVariable String context) {
		List<ItemModel> lst = service.search(context);
		
		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(lst);
    }
	
	/**
	 * View item detail
	 * @param itemId
	 * @return ItemDetailModel
	 */
	@GetMapping("/detail/{id}")
    public ResponseEntity<ItemDetailModel> viewDetail(@PathVariable String itemId) {		
		ItemDetailModel item = service.getItemDetail(Integer.parseInt(itemId));
		
		if (item == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(item);
    }
	
	
	/**
	 * Find all items by seller id.
	 * @param sellerId
	 * @return List<ItemModel>
	 */
	@GetMapping("/all/{sellId}")
    public ResponseEntity<List<ItemModel>> getAllItemsBySeller(@PathVariable Integer sellerId) {
		List<ItemModel> lstModel = service.findAllItems(sellerId);
		
		if (lstModel == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(lstModel);
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
	 * @param itemId
	 * @param stock
	 * @return message
	 */
	@PutMapping("/stock/{itemId}/{stock}")
	public ResponseEntity<MessageModel> updateStock(@PathVariable Integer itemId, @PathVariable Integer stock) {
		try {
			service.updateStock(itemId, stock);
		} catch (BusinessException e) {
			log.error(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessageModel());
		}
		
		return ResponseEntity.ok(null);	
	}
	
}