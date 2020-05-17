package com.emart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emart.model.CartModel;
import com.emart.model.MessageModel;
import com.emart.service.CartService;

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
		
		List<CartModel> lst = service.getCart(Integer.parseInt(userId));
		
		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(lst);
    }
	

	/**
	 * Add item to buyer's cart.
	 * @param buyerId
	 * @param itemId
	 * @param number
	 * @return
	 */
	@PostMapping
	public ResponseEntity<MessageModel> add(
			@RequestParam(value="buyerId") Integer buyerId,
			@RequestParam(value="itemId") Integer itemId,
			@RequestParam(value="number") Integer number) {
		try {
			service.add(buyerId, itemId, number);
			return ResponseEntity.ok(null);
		} catch (IllegalArgumentException e) {
			MessageModel msg = new MessageModel();
			msg.setMessageCode("E999");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
	
	/**
	 * Delete item from buyer's cart.
	 * @param id
	 * @return MessageModel
	 */
	@DeleteMapping
	public ResponseEntity<MessageModel> delete(@RequestParam(value="id") Integer id) {
		try {
			service.delete(id);
			return ResponseEntity.ok(null);
		} catch (IllegalArgumentException e) {
			MessageModel msg = new MessageModel();
			msg.setMessageCode("E999");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
}