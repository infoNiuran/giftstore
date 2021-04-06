package com.niuran.giftstore.response;

import com.niuran.giftstore.model.Delivery;
import com.niuran.giftstore.model.TheOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Rich on 2021-02-21 12:12
 * @Projcet giftstore
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderResponse extends TheOrder implements Serializable {
    private List<OrderDetailResponse> orderDetailList;
    private Delivery delivery;
}
