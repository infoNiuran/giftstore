package com.niuran.giftstore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.dao.OrderLogMapper;
import com.niuran.giftstore.model.OrderLog;
import com.niuran.giftstore.model.OrderLogExample;
import com.niuran.giftstore.request.OrderLogRequest;
import com.niuran.giftstore.service.OrderLogService;
import com.niuran.giftstore.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Rich on 2021-02-21 11:57
 * @Projcet giftstore
 */
@Service
public class OrderLogServiceImpl implements OrderLogService {

    @Autowired
    private OrderLogMapper orderLogMapper;
    @Autowired
    private OrderService orderService;

    public OrderLog getOrderLogById(Long id) {
        if (id == null) {
            return null;
        }
        return orderLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public Msg<OrderLog> createOrderLog(OrderLog orderLog) {
        if (orderLog == null || orderLog.getOrderId() == null || orderLog.getToStatus() == null) {
            return Msg.error("参数错误！");
        }
        OrderLog lastLog = getLastOrderLog(orderLog.getOrderId());
        if (lastLog != null) {
            orderLog.setFromStatus(lastLog.getToStatus());
        }
        orderLogMapper.insertSelective(orderLog);
        return Msg.success(orderLogMapper.selectByPrimaryKey(orderLog.getId()));
    }

    private OrderLog getLastOrderLog(Long orderId) {
        OrderLogExample example = new OrderLogExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        example.setOrderByClause("create_time desc");
        return orderLogMapper.selectOneByExample(example);
    }

    @Override
    public PageInfo<OrderLog> filterPagedOrderLogList(OrderLogRequest request) {
        OrderLogExample example = new OrderLogExample();
        OrderLogExample.Criteria criteria = example.createCriteria();

        if (request.getId() != null) {
            criteria.andIdEqualTo(request.getId());
        }
        if (request.getOrderId() != null) {
            criteria.andOrderIdEqualTo(request.getOrderId());
        }
        if (request.getToStatus() != null) {
            criteria.andToStatusEqualTo(request.getToStatus());
        }
        if (request.getFromStatus() != null) {
            criteria.andFromStatusEqualTo(request.getFromStatus());
        }
        if (StringUtils.isNotEmpty(request.getDescription())) {
            criteria.andDescriptionEqualTo(request.getDescription());
        }
        if (request.getRelationId() != null) {
            criteria.andRelationIdEqualTo(request.getRelationId());
        }
        if (request.getStartTime() != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(request.getStartTime());
        }
        if (request.getEndTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(request.getEndTime());
        }
        if (StringUtils.isNotEmpty(request.getOrderClause())) {
            example.setOrderByClause(request.getOrderClause());
        } else {
            example.setOrderByClause("create_time desc");
        }

        PageHelper.startPage(request.getPage(), request.getSize());

        return new PageInfo<>(orderLogMapper.selectByExample(example));
    }
}
