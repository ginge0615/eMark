package com.emart.service;

import java.math.BigDecimal;

public interface DiscountService {
	
	/**
	 * Get discount by code.
	 * @param code
	 * @return discount percent, if not found or expired then return null.
	 */
	public BigDecimal getDiscount(String code);

}
