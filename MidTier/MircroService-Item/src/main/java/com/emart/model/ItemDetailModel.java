package com.emart.model;

public class ItemDetailModel extends ItemModel{
	private String[] pictures;
	private int number;
	private String[] descriptions;
	private Integer sellerId;
	
	public String[] getPictures() {
		return pictures;
	}
	public void setPictures(String[] pictures) {
		this.pictures = pictures;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String[] getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String[] descriptions) {
		this.descriptions = descriptions;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	
}
