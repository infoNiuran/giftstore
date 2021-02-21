package com.niuran.giftstore.model;

import java.util.Date;

public class Gift {
    private Long id;

    private String code;

    private String name;

    private String description;

    private String images;

    private Long stock;

    private String unit;

    private Long unitPrice;

    private Long unitPriceGoldBean;

    private Integer purchaseOption;

    private Integer shipOption;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getUnitPriceGoldBean() {
        return unitPriceGoldBean;
    }

    public void setUnitPriceGoldBean(Long unitPriceGoldBean) {
        this.unitPriceGoldBean = unitPriceGoldBean;
    }

    public Integer getPurchaseOption() {
        return purchaseOption;
    }

    public void setPurchaseOption(Integer purchaseOption) {
        this.purchaseOption = purchaseOption;
    }

    public Integer getShipOption() {
        return shipOption;
    }

    public void setShipOption(Integer shipOption) {
        this.shipOption = shipOption;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}