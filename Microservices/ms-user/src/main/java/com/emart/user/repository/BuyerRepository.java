package com.emart.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.user.entity.BuyerEntity;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity, Integer>{

	public BuyerEntity findByUsername(String username);

}
