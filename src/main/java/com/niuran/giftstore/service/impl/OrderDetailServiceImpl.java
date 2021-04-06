package com.niuran.giftstore.service.impl;

import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.dao.OrderDetailMapper;
import com.niuran.giftstore.model.*;
import com.niuran.giftstore.request.OrderDetailRequest;
import com.niuran.giftstore.response.OrderDetailResponse;
import com.niuran.giftstore.service.GiftService;
import com.niuran.giftstore.service.GiftSnapshotService;
import com.niuran.giftstore.service.OrderDetailService;
import com.niuran.giftstore.service.OrderService;
import com.niuran.giftstore.util.NullUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Rich on 2021-02-21 11:58
 * @Projcet giftstore
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private GiftService giftService;
    @Autowired
    private GiftSnapshotService snapshotService;

    @Override
    public OrderDetail getOrderDetailById(Long id) {
        if (id == null) {
            return null;
        }
        return orderDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderDetail getOrderDetailByGiftId(Long giftId, Long orderId){
        OrderDetailExample example = new OrderDetailExample();
        OrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);

        List<GiftSnapshot> snapshotList = snapshotService.searchGift(Collections.singletonList(giftId));
        if(snapshotList.size()>0){
            criteria.andGiftSnapshotIdIn(snapshotList.stream().map(GiftSnapshot::getId).collect(Collectors.toList()));
        }
        return orderDetailMapper.selectOneByExample(example);
    }

    @Override
    public OrderDetailResponse getOrderDetailResponseById(Long id) {
        OrderDetail orderDetail = getOrderDetailById(id);
        if (orderDetail == null) {
            return null;
        }

        OrderDetailResponse response = new OrderDetailResponse();
        BeanUtils.copyProperties(orderDetail, response);
        response.setGift(giftService.getGiftById(snapshotService.getGiftSnapshotById(orderDetail.getGiftSnapshotId()).getGiftId()));
        return response;
    }

    @Override
    public Msg<OrderDetail> createOrderDetail(OrderDetail orderDetail) {
        if (orderDetail == null || orderDetail.getGiftSnapshotId() == null || orderDetail.getQuantity() == null
                || orderDetail.getUnitPrice() == null || orderDetail.getPayOption() == null || orderDetail.getOrderId() == null) {
            return Msg.error("参数错误！");
        }
        TheOrder order = orderService.getOrderById(orderDetail.getOrderId());
        if (order == null) {
            return Msg.error("订单不存在！");
        }

        GiftSnapshot giftSnapshot = snapshotService.getGiftSnapshotById(orderDetail.getGiftSnapshotId());
        Gift gift = giftService.getGiftById(giftSnapshot.getGiftId());

        //检查有没有重复商品
        List<OrderDetailResponse> orderDetailList = getOrderDetailListByOrderId(orderDetail.getOrderId());
        boolean sameGift = false;
        Long orderDetailId = null;
        for (OrderDetailResponse detailResponse : orderDetailList) {
            GiftSnapshot snapshot = snapshotService.getGiftSnapshotById(detailResponse.getGiftSnapshotId());
            Gift gift1 = giftService.getGiftById(snapshot.getGiftId());
            if (gift.getId().equals(gift1.getId())) {
                if (snapshot.getCreateTime().getTime() > giftSnapshot.getCreateTime().getTime()) {  //原来订单里面的礼品新
                    detailResponse.setQuantity(detailResponse.getQuantity() + orderDetail.getQuantity());
                } else {
                    BeanUtils.copyProperties(orderDetail, detailResponse, NullUtil.getNullPropertyNames(orderDetail));
                }
                sameGift = true;
                Msg<OrderDetail> msg = updateOrderDetail(detailResponse);
                if (StringUtils.isNotEmpty(msg.getErrorMsg())) {
                    return Msg.error(msg.getErrorMsg());
                } else {
                    orderDetailId = detailResponse.getId();
                }
            }
        }
        if (!sameGift) {
            orderDetailMapper.insertSelective(orderDetail);
            orderDetailId = orderDetail.getId();
        }
        return Msg.success(orderDetailMapper.selectByPrimaryKey(orderDetailId));
    }

    @Override
    public Msg<OrderDetail> updateOrderDetail(OrderDetail orderDetail) {
        if (orderDetail == null || orderDetail.getId() == null) {
            return Msg.error("参数错误！");
        }
        OrderDetail dbOrderDetail = getOrderDetailById(orderDetail.getId());
        if (dbOrderDetail == null) {
            return Msg.error("信息不存在！");
        }

//        GiftSnapshot giftSnapshot = snapshotService.getGiftSnapshotById(orderDetail.getGiftSnapshotId());
//        Gift gift = giftService.getGiftById(giftSnapshot.getGiftId());
//
//        List<OrderDetailResponse> orderDetailList = getOrderDetailListByOrderId(orderDetail.getOrderId());
//        for (OrderDetailResponse detailResponse : orderDetailList) {
//            if(!detailResponse.getId().equals(orderDetail.getId())) {
//                GiftSnapshot snapshot = snapshotService.getGiftSnapshotById(detailResponse.getGiftSnapshotId());
//                Gift gift1 = giftService.getGiftById(snapshot.getGiftId());
//                if (gift.getId().equals(gift1.getId())) {
//                    return Msg.error("礼品重复！");
//                }
//            }
//        }
        orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
        return Msg.success(orderDetailMapper.selectByPrimaryKey(orderDetail.getId()));
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailListByOrderId(Long orderId) {
        OrderDetailExample example = new OrderDetailExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByExample(example);
        List<OrderDetailResponse> responseList = new ArrayList<>();
        for (OrderDetail orderDetail: orderDetailList){
            responseList.add(getOrderDetailResponseById(orderDetail.getId()));
        }
        return responseList;
    }

    @Override
    public PageInfo<OrderDetailResponse> filterPagedOrderDetailList(OrderDetailRequest request){
        OrderDetailExample example = new OrderDetailExample();
        OrderDetailExample.Criteria criteria = example.createCriteria();

        if(request.getOrderId()!=null){
            criteria.andOrderIdEqualTo(request.getOrderId());
        }
        if(request.getId()!=null){
            criteria.andIdEqualTo(request.getId());
        }
        if(request.getGiftSnapshotId()!=null){
            criteria.andGiftSnapshotIdEqualTo(request.getGiftSnapshotId());
        }
        if(request.getStartTime()!=null){
            criteria.andCreateTimeGreaterThanOrEqualTo(request.getStartTime());
        }
        if(request.getEndTime()!=null){
            criteria.andCreateTimeLessThanOrEqualTo(request.getEndTime());
        }
        if(StringUtils.isEmpty(request.getSearch())){
            List<Gift> giftList = giftService.searchName(request.getSearch());
            List<GiftSnapshot> snapshotList = snapshotService.searchGift(giftList.stream().map(Gift::getId).collect(Collectors.toList()));
            if(snapshotList.size()>0){
                criteria.andGiftSnapshotIdIn(snapshotList.stream().map(GiftSnapshot::getId).collect(Collectors.toList()));
            }
        }
        if(StringUtils.isNotEmpty(request.getOrderClause())){
            example.setOrderByClause(request.getOrderClause());
        }else{
            example.setOrderByClause("update_time desc");
        }

        PageInfo<OrderDetail> pageInfo = new PageInfo<>(orderDetailMapper.selectByExample(example));

        PageInfo<OrderDetailResponse> responsePageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,responsePageInfo);

        List<OrderDetailResponse> responseList = new ArrayList<>();
        for(OrderDetail orderDetail: pageInfo.getList()){
            responseList.add(getOrderDetailResponseById(orderDetail.getId()));
        }

        responsePageInfo.setList(responseList);

        return responsePageInfo;
    }

    @Override
    public Msg<Integer> deleteOrderDetail(OrderDetail orderDetail){
        if (orderDetail == null || orderDetail.getId() == null) {
            return Msg.error("参数错误！");
        }
        OrderDetail dbOrderDetail = getOrderDetailById(orderDetail.getId());
        if (dbOrderDetail == null) {
            return Msg.error("信息不存在！");
        }

        orderDetailMapper.deleteByPrimaryKey(orderDetail.getId());
        return Msg.success(1);
    }

    @Override
    public Long salesVolumeOfGift(Long giftId){
        return orderDetailMapper.getSalesVolume(giftId);
    }


    @Override
    public void removeAllItem(Long orderId){
        OrderDetailExample example = new OrderDetailExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        orderDetailMapper.deleteByExample(example);
    }


}
