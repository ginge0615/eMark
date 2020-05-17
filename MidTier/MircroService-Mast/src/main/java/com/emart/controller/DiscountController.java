package com.emart.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.service.DiscountService;

@RestController
@RequestMapping(value = "/discount")
public class DiscountController {
	
	@Autowired
	private DiscountService service;	
	
	/**
	 * Get discount
	 * @param code
	 * @return discount percent, if not found or expired then return null.
	 */
	@GetMapping("/{code}")
    public ResponseEntity<BigDecimal> getDiscount(@PathVariable String code) {
		return ResponseEntity.ok(service.getDiscount(code));
    }

}