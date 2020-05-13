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

import com.emart.model.ItemDetailModel;
import com.emart.model.ItemModel;
import com.emart.service.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
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
	@GetMapping("/{id}")
    public ResponseEntity<ItemDetailModel> viewDetail(@PathVariable String itemId) {		
		ItemDetailModel item = service.getItemDetail(Integer.parseInt(itemId));
		
		if (item == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(item);
    }
}