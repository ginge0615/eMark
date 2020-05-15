package com.emart.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.exception.BusinessException;
import com.emart.model.ItemDetailModel;
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
	
	/**
	 * Get discount
	 * @param code
	 * @return discount percent, if not found or expired then return null.
	 */
	@GetMapping("/discount/{code}")
    public ResponseEntity<BigDecimal> getDiscount(@PathVariable String code) {
		BigDecimal discount = service.getDiscount(code);
		
		if (discount == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(discount);
    }

}