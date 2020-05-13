package com.emart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.model.ItemModel;
import com.emart.service.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
	@Autowired
	private ItemService service;
    
	/**
	 * Add item
	 * @param ItemModel
	 * @return ItemModel
	 */
	@PostMapping
    public ResponseEntity<ItemModel> addItem(@RequestBody ItemModel model) {
		return ResponseEntity.ok(service.addItem(model));
    }
	
	
}