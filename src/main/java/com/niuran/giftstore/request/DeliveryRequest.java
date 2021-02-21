package com.niuran.giftstore.request;

import com.niuran.giftstore.model.Delivery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Rich on 2021-02-21 19:36
 * @Projcet giftstore
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class DeliveryRequest extends Delivery implements Serializable {
    private Integer page = 1;
    private Integer size = 10;
    private Date startTime;
    private Date endTime;
    private String search;
    private String orderClause;
}
