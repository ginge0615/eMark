package com.emart.buyer.model;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseHistoryModel {
	private String id;
	private String category;
	private String subcategory;
	private String manufactur;
	private String picture;
	private String item;
	private BigDecimal price;
	private int num;
	private BigDecimal amount;
	private Date purchaseDateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getManufactur() {
		return manufactur;
	}
	public void setManufactur(String manufactur) {
		this.manufactur = manufactur;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getPurchaseDateTime() {
		return purchaseDateTime;
	}
	public void setPurchaseDateTime(Date purchaseDateTime) {
		this.purchaseDateTime = purchaseDateTime;
	}
	
	
}
