package com.emart.buyer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.buyer.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer>{

	public List<CartEntity> findByBuyerId(String buyerId);

}
