package com.emart.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emart.entity.DiscountEntity;
import com.emart.repository.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	DiscountRepository discountRepository;
	
	/**
	 * Get discount by code.
	 * @param code
	 * @return discount percent, if not found or expired then return null.
	 */
	public BigDecimal getDiscount(String code) {
		Optional<DiscountEntity> optEntity = discountRepository.findByDiscountCode(code);
		
		if (optEntity.isPresent()) {
			DiscountEntity entity = optEntity.get();
			
			Date now = Calendar.getInstance().getTime();
			
			//If within the validity period
			if (now.compareTo(entity.getStartDate()) >= 0 && now.compareTo(entity.getEndDate()) <= 0) {
				return entity.getPercentage();
			}
		}
		
		return null;
	}

}
