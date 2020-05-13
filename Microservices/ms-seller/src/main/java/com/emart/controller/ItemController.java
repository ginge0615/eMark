package com.emart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.exception.BusinessException;
import com.emart.model.ItemDetailModel;
import com.emart.service.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
	private static final Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService service;
    
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
	
}