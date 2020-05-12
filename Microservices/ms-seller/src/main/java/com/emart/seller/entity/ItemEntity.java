package com.emart.seller.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class ItemEntity {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Integer getManufacturId() {
        return manufacturId;
    }

    public void setManufacturId(Integer manufacturId) {
        this.manufacturId = manufacturId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStockNumber(Integer stock) {
        this.stock = stock;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }
}