package com.niuran.giftstore.service;

import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.OrderDetail;
import com.niuran.giftstore.request.OrderDetailRequest;
import com.niuran.giftstore.response.OrderDetailResponse;

import java.util.List;

/**
 * @Author Rich on 2021-02-21 11:57
 * @Projcet giftstore
 */
public interface OrderDetailService {
    OrderDetail getOrderDetailById(Long id);

    OrderDetail getOrderDetailByGiftId(Long giftId, Long orderId);

    OrderDetailResponse getOrderDetailResponseById(Long id);

    Msg<OrderDetail> createOrderDetail(OrderDetail orderDetail);

    Msg<OrderDetail> updateOrderDetail(OrderDetail orderDetail);

    List<OrderDetailResponse> getOrderDetailListByOrderId(Long orderId);

    PageInfo<OrderDetailResponse> filterPagedOrderDetailList(OrderDetailRequest request);

    Msg<Integer> deleteOrderDetail(OrderDetail orderDetail);

    Long salesVolumeOfGift(Long giftId);

    void removeAllItem(Long orderId);
}
