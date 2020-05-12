package com.emart.buyer.model;

import java.util.Date;

public class PurchaseHistoryModel extends ItemModel {
	private int number;
	private Date datetime;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}	
}
