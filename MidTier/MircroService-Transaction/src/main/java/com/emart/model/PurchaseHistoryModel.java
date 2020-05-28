package com.emart.model;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseHistoryModel extends ItemModel {
	private Date datetime;
    private Integer purchaseNumber;
    private BigDecimal transactionAmount;
    private Integer itemId;
	

	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public Integer getPurchaseNumber() {
		return purchaseNumber;
	}
	public void setPurchaseNumber(Integer purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}	
}
