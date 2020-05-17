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
import com.emart.model.MessageModel;
import com.emart.model.TransactionModel;
import com.emart.service.TransactionService;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
	private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private TransactionService service;	
	
	/**
	 * Checkout
	 * @param models TransactionModel[] 
	 * @return MessageModel
	 */
	@PostMapping
	public ResponseEntity<MessageModel> checkout(@RequestBody TransactionModel[] models) {
		MessageModel rtnModel = null;
		
		try {
			service.checkout(models);
		} catch (BusinessException e) {
			log.error(e.toString());
			
			rtnModel = new MessageModel();
			rtnModel.setMessageCode(e.getMessageCode());
			rtnModel.setArgs(e.getArgs());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rtnModel);
		}
		
		return ResponseEntity.ok(rtnModel);
	}
}