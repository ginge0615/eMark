package com.emart.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.emart.entity.ItemViewEntity;
import com.emart.entity.PurchaseHistoryEntity;
import com.emart.model.PurchaseHistoryModel;
import com.emart.model.ReportModel;
import com.emart.repository.ItemViewRepository;
import com.emart.repository.PurchaseHistoryRepository;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {
	@Autowired
	private PurchaseHistoryRepository historyRepository;
	@Autowired
	private ItemViewRepository itemViewRepositor;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * Get purchase history
	 * @param buyerId
	 * @return List<PurchaseHistoryModel>
	 */
	public List<PurchaseHistoryModel> getPurchaseHistory(Integer buyerId) {
		List<PurchaseHistoryEntity> lstEntity = historyRepository.findByBuyerId(buyerId);
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}
		
		List<PurchaseHistoryModel> lstModel = new ArrayList<PurchaseHistoryModel>(lstEntity.size());

		//Convert entity to model   
		lstEntity.stream().forEach(entity -> {
			PurchaseHistoryModel model = new PurchaseHistoryModel();
			
			//Get item from item view
			ItemViewEntity itemViewEntity =  itemViewRepositor.findById(entity.getItemId()).get();
			
			//Copy propeties from ItemViewEntity to cart model
			BeanUtils.copyProperties(itemViewEntity, model);
			//Copy propeties from cart entity to cart model
			BeanUtils.copyProperties(entity, model);
			
			lstModel.add(model);
		});
		
		return lstModel;	
	}

	/**
	 * Get report
	 * @param sellerId
	 * @param item
	 * @param strFromDate
	 * @param strToDate
	 * @return List<ReportModel>
	 */
	@Override
	public List<ReportModel> getReport(Integer sellerId, String item, String strFromDate, String strToDate) throws ParseException  {
		
		//Get seller's history
		List<PurchaseHistoryEntity> lstEntity = historyRepository.findBySellerId(sellerId);
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}
		
		//Filter by FromDate 
		if (!StringUtils.isEmpty(strFromDate)) {
			Date fromDate = sdf.parse(strFromDate); 
			lstEntity = lstEntity.stream().filter(e -> e.getDatetime().compareTo(fromDate) >= 0).collect(Collectors.toList());
		}
		
		//Filter by ToDate 
		if (!StringUtils.isEmpty(strToDate)) {
			Date toDate = sdf.parse(strToDate); 
			lstEntity = lstEntity.stream().filter(e -> e.getDatetime().compareTo(toDate) <= 0).collect(Collectors.toList());
		}
		
		//Group by item id
		Map<Integer, List<PurchaseHistoryEntity>> map = lstEntity.stream().collect(Collectors.groupingBy(e -> e.getItemId()));
		
		List<ReportModel> lstModel = new ArrayList<ReportModel>(map.size());
		
		for(Map.Entry<Integer, List<PurchaseHistoryEntity>> entry : map.entrySet()) {
			//Get item info
			ItemViewEntity itemEntity = itemViewRepositor.findById(entry.getKey()).get();
			
			ReportModel model = new ReportModel();
			BeanUtils.copyProperties(itemEntity, model);
			
			int sumSalesVolume = 0;
			BigDecimal sumTransactionAmount = new BigDecimal(0);
			
			//Sum
			for (PurchaseHistoryEntity e : entry.getValue()) {
				sumSalesVolume += e.getPurchaseNumber();
				sumTransactionAmount = sumTransactionAmount.add(e.getTransactionAmount());
			}
			
			model.setSalesVolume(sumSalesVolume);
			model.setTransactionAmount(sumTransactionAmount);
			
			lstModel.add(model);
		}
		
		//Filter by item name
		if (!"".equals(item)) {
			lstModel = lstModel.stream()
					.filter(m -> m.getItemName().indexOf(item) >= 0)
					.collect(Collectors.toList());
		}
		
		//Sort
		lstModel = lstModel.stream()
				.sorted(Comparator.comparing(ReportModel::getId))
				.collect(Collectors.toList());
		
		return lstModel;
	}

}
