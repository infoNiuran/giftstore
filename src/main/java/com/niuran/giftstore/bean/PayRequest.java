package com.niuran.giftstore.bean;

/**
 * @Author MrD on 2018/8/8.
 * time 9:59
 * 支付请求
 */
public class PayRequest extends PayOrder {
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 设备类型 用于余额支付
     */
    private Integer deviceType;
    /**
     * 用户密码 用于余额支付
     */
    private String password;
    /**
     * openid 用于小程序支付
     */
    private String openId;

    /**
     * 延长展示天数，用于商品延长时间支付
     */
    private Integer extendedShowDays;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getExtendedShowDays() {
        return extendedShowDays;
    }

    public void setExtendedShowDays(Integer extendedShowDays) {
        this.extendedShowDays = extendedShowDays;
    }

    @Override
    public String toString() {
        return "PayRequest{" +
                "version=" + version +
                ", deviceType=" + deviceType +
                ", password='" + password + '\'' +
                ", openId='" + openId + '\'' +
                ", extendedShowDays=" + extendedShowDays +
                "} " + super.toString();
    }
}
