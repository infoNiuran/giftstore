package com.niuran.giftstore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.dao.TheOrderMapper;
import com.niuran.giftstore.enume.OrderStatusEnum;
import com.niuran.giftstore.enume.PurchaseOptionEnum;
import com.niuran.giftstore.enume.ShipOptionEnum;
import com.niuran.giftstore.model.Delivery;
import com.niuran.giftstore.model.Gift;
import com.niuran.giftstore.model.TheOrder;
import com.niuran.giftstore.model.TheOrderExample;
import com.niuran.giftstore.request.OrderRequest;
import com.niuran.giftstore.response.OrderDetailResponse;
import com.niuran.giftstore.response.OrderResponse;
import com.niuran.giftstore.service.DeliveryService;
import com.niuran.giftstore.service.OrderDetailService;
import com.niuran.giftstore.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Rich on 2021-02-21 11:56
 * @Projcet giftstore
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TheOrderMapper orderMapper;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private DeliveryService deliveryService;

    @Override
    public TheOrder getOrderById(Long id) {
        if (id == null) {
            return null;
        }
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderResponse getOrderResponseById(Long id) {
        TheOrder order = getOrderById(id);
        if (order == null) {
            return null;
        }
        OrderResponse response = new OrderResponse();
        BeanUtils.copyProperties(order, response);

        response.setOrderDetailList(orderDetailService.getOrderDetailListByOrderId(id));

        reCalculateOrder(response);
        if (Arrays.asList(OrderStatusEnum.待发货.getValue(), OrderStatusEnum.待签收.getValue(),
                OrderStatusEnum.已签收.getValue()).contains(response.getStatus())) {
            List<Delivery> deliveryList = deliveryService.getDeliveryListByOrderId(response.getId());
            if(deliveryList!=null && deliveryList.size()>0) {
                response.setDelivery(deliveryList.get(0));
            }
        }
        return response;
    }

    private void reCalculateOrder(OrderResponse response) {
        List<OrderDetailResponse> detailResponseList = response.getOrderDetailList();
        if (detailResponseList == null || detailResponseList.size() == 0) {
            response.setTotalPrice(0L);
            response.setTotalPriceGoldBean(0L);
            response.setShipOption(null);
            response.setStatus(OrderStatusEnum.待结算.getValue());
            orderMapper.updateByPrimaryKeySelective(response);
            return;
        }

        Integer shipOption = ShipOptionEnum.无需物流.getValue();
        long total = 0L;
        long totalGoldBean = 0L;
        for (OrderDetailResponse orderDetailResponse : detailResponseList) {
            Gift gift = orderDetailResponse.getGift();
            if (gift == null) {
                continue;
            }
            if (gift.getShipOption().equals(ShipOptionEnum.需物流.getValue())) {
                shipOption = gift.getShipOption();
            }
            if (gift.getPurchaseOption().equals(PurchaseOptionEnum.现金.getValue())) {
                total += gift.getUnitPrice() * orderDetailResponse.getQuantity();
            }
            if (gift.getPurchaseOption().equals(PurchaseOptionEnum.金豆.getValue())) {
                totalGoldBean += gift.getUnitPriceGoldBean() * orderDetailResponse.getQuantity();
            }
        }
        response.setShipOption(shipOption);
        response.setTotalPrice(total);
        response.setTotalPriceGoldBean(totalGoldBean);
        orderMapper.updateByPrimaryKeySelective(response);
    }

    @Override
    public Msg<TheOrder> createOrder(TheOrder order) {
        if (order == null || order.getUserId() == null) {
            return Msg.error("参数错误！");
        }
        if (order.getStatus() == null) {
            order.setStatus(OrderStatusEnum.待结算.getValue());
        }
        orderMapper.insertSelective(order);
        return Msg.success(orderMapper.selectByPrimaryKey(order.getId()));
    }

    @Override
    public Msg<TheOrder> updateOrder(TheOrder order) {
        if (order == null || order.getId() == null) {
            return Msg.error("参数错误！");
        }
        TheOrder dbOrder = getOrderById(order.getId());
        if (dbOrder == null) {
            return Msg.error("订单不存在！");
        }
        orderMapper.updateByPrimaryKeySelective(order);
        return Msg.success(orderMapper.selectByPrimaryKey(order.getId()));
    }

    @Override
    public Msg<TheOrder> deleteOrder(TheOrder order) {
        if (order == null || order.getId() == null) {
            return Msg.error("参数错误！");
        }
        TheOrder dbOrder = getOrderById(order.getId());
        if (dbOrder == null) {
            return Msg.error("订单不存在！");
        }
        order.setStatus(OrderStatusEnum.已删除.getValue());
        orderMapper.updateByPrimaryKeySelective(order);
        return Msg.success(orderMapper.selectByPrimaryKey(order.getId()));
    }

    @Override
    public PageInfo<TheOrder> filterPagedOrderList(OrderRequest request) {
        TheOrderExample example = new TheOrderExample();
        TheOrderExample.Criteria criteria = example.createCriteria();

        if (request.getId() != null) {
            criteria.andIdEqualTo(request.getId());
        }
        if (request.getUserId() != null) {
            criteria.andUserIdEqualTo(request.getUserId());
        }
        if (request.getStatus() != null) {
            criteria.andStatusEqualTo(request.getStatus());
        }
        if(request.getStatusList()!=null && request.getStatusList().size()>0){
            criteria.andStatusIn(request.getStatusList());
        }
        if (request.getShipOption() != null) {
            criteria.andShipOptionEqualTo(request.getShipOption());
        }
        if (request.getStartTime() != null) {
            criteria.andCreateTimeGreaterThan(request.getStartTime());
        }
        if (request.getEndTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(request.getEndTime());
        }
        if (StringUtils.isNotEmpty(request.getOrderClause())) {
            example.setOrderByClause(request.getOrderClause());
        } else {
            example.setOrderByClause("update_time desc");
        }

        PageHelper.startPage(request.getPage(), request.getSize());
        return new PageInfo<>(orderMapper.selectByExample(example));
    }

    @Override
    public PageInfo<OrderResponse> filterPagedOrderResponseList(OrderRequest request) {
        PageInfo<TheOrder> pageInfo = filterPagedOrderList(request);
        PageInfo<OrderResponse> responsePageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, responsePageInfo);

        List<OrderResponse> responseList = new ArrayList<>();
        for (TheOrder order : pageInfo.getList()) {
            responseList.add(getOrderResponseById(order.getId()));
        }
        responsePageInfo.setList(responseList);
        return responsePageInfo;
    }

    @Override
    public OrderResponse getLastUnPaidOrder(Long userId) {
        TheOrderExample example = new TheOrderExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(OrderStatusEnum.待结算.getValue());
        example.setOrderByClause("create_time desc");
        TheOrder order = orderMapper.selectOneByExample(example);
        if(order==null){
            return null;
        }
        return getOrderResponseById(order.getId());
    }


}
