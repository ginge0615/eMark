package com.emart.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="item_view")
public class ItemViewEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="sell_id")
    private Integer sellId;

    @Column(name="category_id")
    private Integer categoryId;

    @Column(name="subcategory_id")
    private Integer subcategoryId;

    @Column(name="manufactur_id")
    private Integer manufacturId;

    @Column(name="item_name")
    private String itemName;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="stock")
    private Integer stock;

    @Column(name="sales_volume")
    private Integer salesVolume;
    
    private String seller;
    
    private String picture;

    private String categroy;

    private String subcategroy;

    private String manufactur;    

    @Column(name="search_context")
    private String searchContext;

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCategroy() {
		return categroy;
	}

	public void setCategroy(String categroy) {
		this.categroy = categroy;
	}

	public String getSubcategroy() {
		return subcategroy;
	}

	public void setSubcategroy(String subcategroy) {
		this.subcategroy = subcategroy;
	}

	public String getManufactur() {
		return manufactur;
	}

	public void setManufactur(String manufactur) {
		this.manufactur = manufactur;
	}

	public String getSearchContext() {
		return searchContext;
	}

	public void setSearchContext(String searchContext) {
		this.searchContext = searchContext;
	}
}