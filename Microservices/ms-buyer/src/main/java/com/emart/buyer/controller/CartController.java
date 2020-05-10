package com.emart.buyer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.buyer.model.CartModel;
import com.emart.buyer.service.CartService;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
	@Autowired
	private CartService service;
	
	/**
	 * Get cart
	 * @param userId
	 * @return List<ItemModel>
	 */
	@GetMapping("/{userId}")
    public ResponseEntity<List<CartModel>> getCart(@PathVariable String userId) {
		List<CartModel> lst = service.getCart(userId);
		
		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(lst);
    }
	
	/**
	 * Add item to buyer's cart.
	 * @param model CartModel
	 * @return the number of items in buyer's cart
	 */
	@PostMapping("/add")
	public ResponseEntity<Integer> addToCart(@RequestBody CartModel model) {
		return ResponseEntity.ok(service.add(model));
	}
	
	/**
	 * Delete item from buyer's cart.
	 * @param model CartModel
	 * @return the number of items in buyer's cart
	 */
	@DeleteMapping
	public ResponseEntity<Integer> delete(@RequestBody CartModel model) {
		return ResponseEntity.ok(service.delete(model));
	}
}