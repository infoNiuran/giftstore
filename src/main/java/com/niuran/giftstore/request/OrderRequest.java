package com.niuran.giftstore.request;

import com.niuran.giftstore.bean.Address;
import com.niuran.giftstore.model.Gift;
import com.niuran.giftstore.model.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Rich on 2021-02-21 14:26
 * @Projcet giftstore
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderRequest extends Order implements Serializable {
    private List<Gift> giftList;
    private Integer page = 1;
    private Integer size = 10;
    private Date startTime;
    private Date endTime;
    private String orderClause;

    //支付时使用
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 设备类型 用于余额支付
     *    android(0), ios(1), js(2);
     */
    private Integer deviceType;
    /**
     * 用户密码 用于金豆和余额支付
     */
    private String password;
    /**
     * openid 用于小程序支付
     */
    private String openId;

    private Address address;
}
