package com.niuran.giftstore.model;

import java.util.Date;

public class TheOrder {
    private Long id;

    private Long userId;

    private Long totalPrice;

    private Long totalPriceGoldBean;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private Integer shipOption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getTotalPriceGoldBean() {
        return totalPriceGoldBean;
    }

    public void setTotalPriceGoldBean(Long totalPriceGoldBean) {
        this.totalPriceGoldBean = totalPriceGoldBean;
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

    public Integer getShipOption() {
        return shipOption;
    }

    public void setShipOption(Integer shipOption) {
        this.shipOption = shipOption;
    }
}