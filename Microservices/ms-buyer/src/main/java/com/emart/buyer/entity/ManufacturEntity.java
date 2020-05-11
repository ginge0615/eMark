package com.emart.buyer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="manufactur")
public class ManufacturEntity {
    private Integer id;

    @Column(name="manufactur_name")
    private String manufacturName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManufacturName() {
        return manufacturName;
    }

    public void setManufacturName(String manufacturName) {
        this.manufacturName = manufacturName == null ? null : manufacturName.trim();
    }
}