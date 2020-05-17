package com.emart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.model.OptionModel;
import com.emart.service.ManufacturService;

@RestController
@RequestMapping(value = "/manufactur")
public class ManufacturController {
	
	@Autowired
	private ManufacturService service;

	/**
	 * Get all manufacturs
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<OptionModel>> getAllManufacturs() {
		List<OptionModel> lst = service.getAllManufacturs();
		
		if (lst == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(lst);
	}
	
}
