package com.emart.buyer.model;

public class ItemDetailModel extends ItemModel{
	private String[] pictures;
	private int number;
	private String[] descriptions;
	
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
	
}
