package com.niuran.giftstore.bean;

import java.util.Date;

public class PayOrder {
    private Long id;

    private Long userId;

    private String orderNo;

    private String orderDesc;

    private String productName;

    private Integer targetType;

    private Integer payType;

    private Long productSaleId;  //关联产品编号

    private Long couponId;

    private Long buyAmount;

    private Long payAmount;

    private Integer status;

    private Date createTimeStamp;

    private Date updateTimeStamp;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc == null ? null : orderDesc.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getProductSaleId() {
        return productSaleId;
    }

    public void setProductSaleId(Long productSaleId) {
        this.productSaleId = productSaleId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Long buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Date createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public Date getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(Date updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    @Override
    public String toString() {
        return "PayOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderNo='" + orderNo + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                ", productName='" + productName + '\'' +
                ", targetType=" + targetType +
                ", payType=" + payType +
                ", productSaleId=" + productSaleId +
                ", couponId=" + couponId +
                ", buyAmount=" + buyAmount +
                ", payAmount=" + payAmount +
                ", status=" + status +
                ", createTimeStamp=" + createTimeStamp +
                ", updateTimeStamp=" + updateTimeStamp +
                '}';
    }
}