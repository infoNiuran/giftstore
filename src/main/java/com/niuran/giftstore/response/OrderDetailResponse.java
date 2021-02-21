package com.niuran.giftstore.response;

import com.niuran.giftstore.model.Gift;
import com.niuran.giftstore.model.OrderDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author Rich on 2021-02-21 12:19
 * @Projcet giftstore
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderDetailResponse extends OrderDetail implements Serializable {
    private Gift gift;
}
