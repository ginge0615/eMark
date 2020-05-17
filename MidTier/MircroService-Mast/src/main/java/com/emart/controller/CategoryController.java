package com.emart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.model.OptionModel;
import com.emart.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;

	/**
	 * Get all categories
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<OptionModel>> getAllCategories() {
		List<OptionModel> lst = service.getAllCategories();
		
		if (lst == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(lst);
	}
	
	/**
	 * Get sub categories
	 * @return
	 */
	@GetMapping("/{categoryId}")
	public ResponseEntity<List<OptionModel>> getSubCategories(@PathVariable String categoryId) {
		List<OptionModel> lst = service.getSubCategories(categoryId);
		
		if (lst == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(lst);
	}
}
