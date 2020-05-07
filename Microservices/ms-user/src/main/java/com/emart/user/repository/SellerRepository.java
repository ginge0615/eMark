package com.emart.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.user.entity.SellerEntity;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Integer>{

	public SellerEntity findByUsername(String username);

}
