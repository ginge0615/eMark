package com.emart.buyer.model;

import java.math.BigDecimal;

public class ItemDetailModel {
	private String id;
	private String[] pictures;
	private String category;
	private String subcategory;
	private String manufactur;
	private String item;
	private BigDecimal price;
	private String seller;
	private int volume;
	private int num;
	private BigDecimal tax;
	private String[] descriptions;
	private String sellerId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getPictures() {
		return pictures;
	}
	public void setPictures(String[] pictures) {
		this.pictures = pictures;
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
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String[] getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String[] descriptions) {
		this.descriptions = descriptions;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
}
