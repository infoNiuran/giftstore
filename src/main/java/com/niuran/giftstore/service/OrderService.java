package com.niuran.giftstore.service;

import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.Order;
import com.niuran.giftstore.request.OrderRequest;
import com.niuran.giftstore.response.OrderResponse;

/**
 * @Author Rich on 2021-02-21 11:56
 * @Projcet giftstore
 */
public interface OrderService {
    Order getOrderById(Long id);

    OrderResponse getOrderResponseById(Long id);

    Msg<Order> createOrder(Order order);

    Msg<Order> updateOrder(Order order);

    Msg<Order> deleteOrder(Order order);

    PageInfo<Order> filterPagedOrderList(OrderRequest request);
}
