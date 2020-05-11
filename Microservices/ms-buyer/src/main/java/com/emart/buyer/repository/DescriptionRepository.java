package com.emart.buyer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.buyer.entity.DescriptionEntity;
import com.emart.buyer.entity.DiscountEntity;

@Repository
public interface DescriptionRepository extends JpaRepository<DiscountEntity, Integer>{

	public List<DescriptionEntity> findByItemId(Integer itemId);

}
