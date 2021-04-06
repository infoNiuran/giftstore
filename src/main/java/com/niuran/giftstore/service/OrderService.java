package com.niuran.giftstore.service;

import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.TheOrder;
import com.niuran.giftstore.request.OrderRequest;
import com.niuran.giftstore.response.OrderResponse;

/**
 * @Author Rich on 2021-02-21 11:56
 * @Projcet giftstore
 */
public interface OrderService {
    TheOrder getOrderById(Long id);

    OrderResponse getOrderResponseById(Long id);

    Msg<TheOrder> createOrder(TheOrder order);

    Msg<TheOrder> updateOrder(TheOrder order);

    Msg<TheOrder> deleteOrder(TheOrder order);

    PageInfo<TheOrder> filterPagedOrderList(OrderRequest request);

    PageInfo<OrderResponse> filterPagedOrderResponseList(OrderRequest request);

    OrderResponse getLastUnPaidOrder(Long userId);
}
