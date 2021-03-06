package com.emart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.entity.SellerEntity;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Integer>{

	public Optional<SellerEntity> findByUsername(String username);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByCompanyName(String companyName);

}
