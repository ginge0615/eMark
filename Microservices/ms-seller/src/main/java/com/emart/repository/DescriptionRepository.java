package com.emart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.entity.DescriptionEntity;

@Repository
public interface DescriptionRepository extends JpaRepository<DescriptionEntity, Integer>{

	public List<DescriptionEntity> findByItemId(Integer itemId);

}
