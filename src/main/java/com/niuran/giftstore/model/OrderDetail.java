package com.niuran.giftstore.model;

import java.util.Date;

public class OrderDetail {
    private Long id;

    private Long orderId;

    private Long giftSnapshotId;

    private Long quantity;

    private Long unitPrice;

    private Integer payOption;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGiftSnapshotId() {
        return giftSnapshotId;
    }

    public void setGiftSnapshotId(Long giftSnapshotId) {
        this.giftSnapshotId = giftSnapshotId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getPayOption() {
        return payOption;
    }

    public void setPayOption(Integer payOption) {
        this.payOption = payOption;
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
}