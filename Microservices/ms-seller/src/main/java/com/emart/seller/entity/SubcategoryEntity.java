package com.emart.seller.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="subcategory")
public class SubcategoryEntity {
    private Integer id;

    @Column(name="subcategory_name")
    private String subcategoryName;

    @Column(name="category_id")
    private Integer categoryId;

    @Column(name="brief_details")
    private String briefDetails;

    @Column(name="GST")
    private BigDecimal gst;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName == null ? null : subcategoryName.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getBriefDetails() {
        return briefDetails;
    }

    public void setBriefDetails(String briefDetails) {
        this.briefDetails = briefDetails == null ? null : briefDetails.trim();
    }

    public BigDecimal getGst() {
        return gst;
    }

    public void setGst(BigDecimal gst) {
        this.gst = gst;
    }
}