package com.emart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer>{

	public List<CartEntity> findByBuyerId(Integer buyerId);
	
	public Optional<CartEntity> findByBuyerIdAndItemId(Integer buyerId, Integer itemId);

}
