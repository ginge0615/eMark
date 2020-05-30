package com.emart.model;

import java.math.BigDecimal;

public class ReportModel extends ItemModel {
    private BigDecimal transactionAmount;    

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
}