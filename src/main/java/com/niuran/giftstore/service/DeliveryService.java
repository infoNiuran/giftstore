package com.niuran.giftstore.service;

import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.Delivery;
import com.niuran.giftstore.request.DeliveryRequest;

import java.util.List;

/**
 * @Author Rich on 2021-02-21 16:22
 * @Projcet giftstore
 */
public interface DeliveryService {
    Delivery getDeliveryById(Long id);

    List<Delivery> getDeliveryListByOrderId(Long orderId);

    Msg<Delivery> createDelivery(Delivery delivery);

    Msg<Delivery> updateDelivery(Delivery delivery);

    PageInfo<Delivery> filterPagedDeliveryList(DeliveryRequest request);
}
