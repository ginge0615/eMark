package com.emart.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emart.entity.ReportViewEntity;

@Repository
public interface ReportViewRepository extends JpaRepository<ReportViewEntity, Integer>{

	/**
	 * Search report from report view
	 * @param sellerId
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	@Query(value="select * from report_view where seller_id = ?1 " + 
	             "and ( item like %?2% or ?2 is null)" +
	             "and ( datetime >= ?3 or ?3 is null)" +
	             "and ( datetime <= ?4 or ?4 is null)",nativeQuery = true)
	public List<ReportViewEntity> searchReport(Integer sellerId, String item, Date fromDate, Date toDate);

}
