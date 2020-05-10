package com.emart.buyer.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="item_view")
public class ItemViewEntity {
    private Integer id;

    private String item;

    private String seller;
    
    private String picture;

    private String categroy;

    private String subcategroy;

    private String manufactur;

    private BigDecimal price;

    private Integer volume;

    @Column(name="search_context")
    private String searchContext;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    public String getCategroy() {
        return categroy;
    }

    public void setCategroy(String categroy) {
        this.categroy = categroy == null ? null : categroy.trim();
    }

    public String getSubcategroy() {
        return subcategroy;
    }

    public void setSubcategroy(String subcategroy) {
        this.subcategroy = subcategroy == null ? null : subcategroy.trim();
    }

    public String getManufactur() {
        return manufactur;
    }

    public void setManufactur(String manufactur) {
        this.manufactur = manufactur == null ? null : manufactur.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getSearchContext() {
        return searchContext;
    }

    public void setSearchContext(String searchContext) {
        this.searchContext = searchContext == null ? null : searchContext.trim();
    }

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}