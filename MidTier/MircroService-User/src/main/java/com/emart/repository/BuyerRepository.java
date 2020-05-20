package com.emart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.entity.BuyerEntity;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity, Integer>{

	public Optional<BuyerEntity> findByUsername(String username);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByMobilePhone(String mobilePhone);
	

}
