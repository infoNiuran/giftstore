package com.niuran.giftstore.service;

import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.OrderLog;
import com.niuran.giftstore.request.OrderLogRequest;

/**
 * @Author Rich on 2021-02-21 11:57
 * @Projcet giftstore
 */
public interface OrderLogService {
    Msg<OrderLog> createOrderLog(OrderLog orderLog);

    PageInfo<OrderLog> filterPagedOrderLogList(OrderLogRequest request);
}
