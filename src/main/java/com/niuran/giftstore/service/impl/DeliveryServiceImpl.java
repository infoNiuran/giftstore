package com.niuran.giftstore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.dao.DeliveryMapper;
import com.niuran.giftstore.enume.DeliveryStatusEnum;
import com.niuran.giftstore.model.Delivery;
import com.niuran.giftstore.model.DeliveryExample;
import com.niuran.giftstore.request.DeliveryRequest;
import com.niuran.giftstore.service.DeliveryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Rich on 2021-02-21 16:22
 * @Projcet giftstore
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Override
    public Delivery getDeliveryById(Long id) {
        if (id == null) {
            return null;
        }
        return deliveryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Delivery> getDeliveryListByOrderId(Long orderId) {
        DeliveryExample example = new DeliveryExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        example.setOrderByClause("update_time desc");
        return deliveryMapper.selectByExample(example);
    }

    @Override
    public Msg<Delivery> createDelivery(Delivery delivery) {
        if (delivery == null || StringUtils.isEmpty(delivery.getReceiverInfo()) || delivery.getOrderId() == null) {
            return Msg.error("参数错误！");
        }
        delivery.setStatus(DeliveryStatusEnum.创建.getValue());
        deliveryMapper.insertSelective(delivery);
        return Msg.success(deliveryMapper.selectByPrimaryKey(delivery.getId()));
    }

    @Override
    public Msg<Delivery> updateDelivery(Delivery delivery) {
        if (delivery == null || delivery.getId() == null) {
            return Msg.error("参数错误！");
        }
        Delivery dbDelivery = getDeliveryById(delivery.getId());
        if (null == dbDelivery) {
            return Msg.error("信息不存在！");
        }
        deliveryMapper.updateByPrimaryKeySelective(delivery);
        return Msg.success(deliveryMapper.selectByPrimaryKey(delivery.getId()));
    }

    @Override
    public PageInfo<Delivery> filterPagedDeliveryList(DeliveryRequest request) {
        DeliveryExample example = new DeliveryExample();
        DeliveryExample.Criteria criteria = example.createCriteria();

        if (request.getId() != null) {
            criteria.andIdEqualTo(request.getId());
        }
        if (request.getOrderId() != null) {
            criteria.andOrderIdEqualTo(request.getOrderId());
        }
        if (request.getStatus() != null) {
            criteria.andStatusEqualTo(request.getStatus());
        }
        if (request.getCarrierId() != null) {
            criteria.andCarrierIdEqualTo(request.getCarrierId());
        }
        if (request.getStartTime() != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(request.getStartTime());
        }
        if (request.getEndTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(request.getEndTime());
        }
        if (StringUtils.isNotEmpty(request.getSearch())) {
            DeliveryExample.Criteria criteria1 = example.createCriteria();
            BeanUtils.copyProperties(criteria,criteria1);
            criteria1.getCriteria().addAll(criteria.getAllCriteria());
            criteria.andReceiverInfoLike("%" + request.getSearch() + "%");
            criteria1.andWaybillLike("%" + request.getSearch() + "%");
            example.or(criteria1);
        }
        if(StringUtils.isNotEmpty(request.getOrderClause())){
            example.setOrderByClause(request.getOrderClause());
        }else{
            example.setOrderByClause("update_time desc");
        }

        PageHelper.startPage(request.getPage(),request.getSize());

        return new PageInfo<>(deliveryMapper.selectByExample(example));
    }
}
