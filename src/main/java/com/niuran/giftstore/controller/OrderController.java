package com.niuran.giftstore.controller;

import com.alibaba.fastjson.JSON;
import com.niuran.giftstore.bean.*;
import com.niuran.giftstore.enume.OrderStatusEnum;
import com.niuran.giftstore.enume.PurchaseOptionEnum;
import com.niuran.giftstore.enume.ShipOptionEnum;
import com.niuran.giftstore.feign.FeignOrderService;
import com.niuran.giftstore.feign.FeignUserInfoService;
import com.niuran.giftstore.model.*;
import com.niuran.giftstore.net.CurrentUser;
import com.niuran.giftstore.net.LoginRequired;
import com.niuran.giftstore.request.GiftRequest;
import com.niuran.giftstore.request.OrderRequest;
import com.niuran.giftstore.response.CartDetailResponse;
import com.niuran.giftstore.response.CartResponse;
import com.niuran.giftstore.response.OrderDetailResponse;
import com.niuran.giftstore.response.OrderResponse;
import com.niuran.giftstore.response.base.DataResponse;
import com.niuran.giftstore.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Rich on 2021-02-21 14:15
 * @Projcet giftstore
 */
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/orderController")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private GiftService giftService;
    @Autowired
    private GiftSnapshotService snapshotService;
    @Autowired
    private FeignUserInfoService feignUserInfoService;
    @Autowired
    private FeignOrderService feignOrderService;
    @Autowired
    private OrderLogService orderLogService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartDetailService cartDetailService;

    @LoginRequired
    @PostMapping("/getOrderResponseById")
    public DataResponse getOrderResponseById(@RequestBody TheOrder order) {
        if (order == null || order.getId() == null) {
            return DataResponse.error("参数错误！");
        }
        OrderResponse orderResponse = orderService.getOrderResponseById(order.getId());
        if (orderResponse == null) {
            return DataResponse.error("订单不存在！");
        }
        return DataResponse.success(orderResponse);
    }

    @LoginRequired
    @PostMapping("/createOrder")
    public DataResponse createOrder(@RequestBody TheOrder order) {
        Msg<TheOrder> msg = orderService.createOrder(order);
        if (StringUtils.isNotEmpty(msg.getErrorMsg())) {
            return DataResponse.error(msg.getErrorMsg());
        }
        orderLogService.createOrderLog(new OrderLog() {{
            setOrderId(msg.getData().getId());
            setToStatus(OrderStatusEnum.待结算.getValue());
        }});
        return DataResponse.success(msg.getData());
    }

    @LoginRequired
    @PostMapping("/createOrderFromCart")
    public DataResponse createOrderFromCart(@CurrentUser UserBaseInfo userBaseInfo) {
        CartResponse cartResponse = cartService.getCartResponseByUserId(userBaseInfo.getId());
        if (cartResponse == null) {
            return DataResponse.error("参数错误！");
        }

        if (cartResponse.getCartDetailList() == null || cartResponse.getCartDetailList().size() == 0) {
            return DataResponse.error("购物车是空的");
        }

        //检查是不是有用户没有结算的order
        OrderResponse lastUnPaidOrder = orderService.getLastUnPaidOrder(cartResponse.getUserId());
        if (lastUnPaidOrder != null) {
            for (OrderDetailResponse orderDetailResponse : lastUnPaidOrder.getOrderDetailList()) {
                orderDetailService.deleteOrderDetail(orderDetailResponse);
            }
        } else {
            TheOrder paramOrder = new TheOrder();
            paramOrder.setUserId(cartResponse.getUserId());
            Msg<TheOrder> msg = orderService.createOrder(paramOrder);
            if (StringUtils.isNotEmpty(msg.getErrorMsg())) {
                return DataResponse.error(msg.getErrorMsg());
            }
            orderLogService.createOrderLog(new OrderLog() {{
                setOrderId(msg.getData().getId());
                setToStatus(OrderStatusEnum.待结算.getValue());
            }});
            lastUnPaidOrder = orderService.getOrderResponseById(msg.getData().getId());
        }

        for (CartDetailResponse cartDetailResponse : cartResponse.getCartDetailList()) {
            if(cartDetailResponse.getChecked()>0) {
                OrderDetail paramOrderDetail = new OrderDetail();
                paramOrderDetail.setOrderId(lastUnPaidOrder.getId());
                paramOrderDetail.setQuantity(cartDetailResponse.getQuantity());
                paramOrderDetail.setUnitPrice(cartDetailResponse.getGift().getUnitPriceGoldBean());
                paramOrderDetail.setPayOption(cartDetailResponse.getGift().getPurchaseOption());

                GiftSnapshot snapshot = new GiftSnapshot();
                snapshot.setGiftId(cartDetailResponse.getGiftId());
                Msg<GiftSnapshot> snapshotMsg = snapshotService.createGiftSnapshot(snapshot);
                if (StringUtils.isNotEmpty(snapshotMsg.getErrorMsg())) {
                    return DataResponse.error(snapshotMsg.getErrorMsg());
                }
                paramOrderDetail.setGiftSnapshotId(snapshotMsg.getData().getId());

                orderDetailService.createOrderDetail(paramOrderDetail);
            }
        }

        return DataResponse.success(orderService.getOrderResponseById(lastUnPaidOrder.getId()));
    }

    @LoginRequired
    @PostMapping("/deleteOrder")
    public DataResponse deleteOrder(@RequestBody TheOrder order) {
        Msg<TheOrder> msg = orderService.deleteOrder(order);
        if (StringUtils.isNotEmpty(msg.getErrorMsg())) {
            return DataResponse.error(msg.getErrorMsg());
        }
        orderLogService.createOrderLog(new OrderLog() {{
            setOrderId(msg.getData().getId());
            setToStatus(OrderStatusEnum.已删除.getValue());
        }});
        return DataResponse.success(msg.getData());
    }

    @LoginRequired
    @PostMapping("/cancelOrder")
    public DataResponse cancelOrder(@RequestBody TheOrder order) {
        order.setStatus(OrderStatusEnum.已取消.getValue());
        Msg<TheOrder> msg = orderService.updateOrder(order);
        if (StringUtils.isNotEmpty(msg.getErrorMsg())) {
            return DataResponse.error(msg.getErrorMsg());
        }
        orderLogService.createOrderLog(new OrderLog() {{
            setOrderId(msg.getData().getId());
            setToStatus(OrderStatusEnum.已取消.getValue());
        }});
        return DataResponse.success(msg.getData());
    }

    @LoginRequired
    @PostMapping("/refreshOrder")
    public DataResponse refreshOrder(@RequestBody TheOrder order) {
        if (order == null || order.getId() == null) {
            return DataResponse.error("参数错误！");
        }
        OrderResponse orderResponse = orderService.getOrderResponseById(order.getId());
        if (orderResponse == null) {
            return DataResponse.error("订单不存在！");
        }
        return DataResponse.success(orderResponse);
    }

    @LoginRequired
    @PostMapping("/addGiftsToOrder")
    public DataResponse addGiftsToOrder(@RequestBody OrderRequest request) {
        if (request == null || request.getId() == null || request.getGiftList() == null || request.getGiftList().size() == 0) {
            return DataResponse.error("参数错误！");
        }
        TheOrder order = orderService.getOrderById(request.getId());
        if (order == null) {
            return DataResponse.error("订单不存在！");
        }
        if (!order.getStatus().equals(OrderStatusEnum.待结算.getValue())) {
            return DataResponse.error("当前订单状态为" + OrderStatusEnum.getEnum(order.getStatus()) + "，不能添加礼品！");
        }
        orderDetailService.removeAllItem(order.getId());

        for (GiftRequest gift : request.getGiftList()) {
            Gift oGift = giftService.getGiftById(gift.getId());
            if (oGift == null) {
                return DataResponse.error("礼品信息错误！");
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setQuantity(gift.getQuantity());
            orderDetail.setPayOption(oGift.getPurchaseOption());
            if (oGift.getPurchaseOption().equals(PurchaseOptionEnum.现金.getValue())) {
                orderDetail.setUnitPrice(oGift.getUnitPrice());
            } else if (oGift.getPurchaseOption().equals(PurchaseOptionEnum.金豆.getValue())) {
                orderDetail.setUnitPrice(oGift.getUnitPriceGoldBean());
            }

            Msg<GiftSnapshot> msg = snapshotService.createGiftSnapshot(new GiftSnapshot() {{
                setGiftId(oGift.getId());
            }});

            if (StringUtils.isNotEmpty(msg.getErrorMsg())) {
                return DataResponse.error(msg.getErrorMsg());
            } else {
                orderDetail.setGiftSnapshotId(msg.getData().getId());
            }

            Msg<OrderDetail> msg1 = orderDetailService.createOrderDetail(orderDetail);
            if (StringUtils.isNotEmpty(msg1.getErrorMsg())) {
                return DataResponse.error(msg1.getErrorMsg());
            }
        }
        return DataResponse.success(getOrderResponseById(order));
    }

    @LoginRequired
    @PostMapping("/removeGiftsFromOrder")
    public DataResponse removeGiftsFromOrder(@RequestBody OrderRequest request) {
        if (request == null || request.getId() == null) {
            return DataResponse.error("参数错误！");
        }
        TheOrder order = orderService.getOrderById(request.getId());
        if (order == null) {
            return DataResponse.error("订单不存在！");
        }

        int failed = 0;
        for (Gift gift : request.getGiftList()) {
            Gift oGift = giftService.getGiftById(gift.getId());
            if (oGift == null) {
                return DataResponse.error("礼品信息错误！");
            }

            OrderDetail orderDetail = orderDetailService.getOrderDetailByGiftId(gift.getId(), order.getId());
            if (orderDetail == null) {
                failed++;
                continue;
            }

            Msg<Integer> msg = orderDetailService.deleteOrderDetail(orderDetail);
            if (StringUtils.isNotEmpty(msg.getErrorMsg())) {
                failed++;
            }
        }
        if (failed > 0) {
            return DataResponse.error("有" + failed + "条礼品删除失败！");
        }
        return DataResponse.success(getOrderResponseById(order));
    }

    @LoginRequired
    @PostMapping("/payOrderByGoldBean")
    public DataResponse payOrderByGoldBean(@CurrentUser UserBaseInfo userBaseInfo, @RequestBody OrderRequest request) {
        if (request == null || request.getId() == null) {
            return DataResponse.error("参数错误！");
        }
        OrderResponse orderResponse = orderService.getOrderResponseById(request.getId());
        if (orderResponse == null) {
            return DataResponse.error("订单不存在！");
        }
        if (!orderResponse.getStatus().equals(OrderStatusEnum.待结算.getValue())) {
            return DataResponse.error("当前订单状态为" + OrderStatusEnum.getEnum(orderResponse.getStatus()) + "，不能进行结算！");
        }
        if (orderResponse.getTotalPriceGoldBean() <= 0) {
            return DataResponse.error("结算金额金豆数不对！");
        }
        if (!orderResponse.getUserId().equals(userBaseInfo.getId())) {
            return DataResponse.error("非本人订单，不能结算！");
        }

        if (orderResponse.getShipOption().equals(ShipOptionEnum.需物流.getValue())) {
            if(request.getAddress()==null) {
                List<Delivery> deliveryList = deliveryService.getDeliveryListByOrderId(request.getId());
                if (deliveryList.size() == 0 || StringUtils.isEmpty(deliveryList.get(0).getReceiverInfo())) {
                    return DataResponse.error("请先设置发货地址！");
                }
            }else{
                //更新或创建发货记录
                Msg<Delivery> deliveryMsg = deliveryService.createDelivery(new Delivery() {{
                    setOrderId(request.getId());
                    setReceiverInfo(JSON.toJSONString(request.getAddress()));
                }});
                if(StringUtils.isNotEmpty(deliveryMsg.getErrorMsg())){
                    return DataResponse.error(deliveryMsg.getErrorMsg());
                }
            }
        }

        DataResponse dataResponse = feignUserInfoService.getUserAccountInfoByUserId(userBaseInfo.getId());
        if (dataResponse.getRetCode() != 0) {
            return dataResponse;
        } else if (dataResponse.getData() == null) {
            return DataResponse.error("用户账户信息错误！");
        }

        UserAccountInfo userAccountInfo = JSON.parseObject(JSON.toJSONString(dataResponse.getData()), UserAccountInfo.class);

        if (userAccountInfo == null || userAccountInfo.getBeans() < orderResponse.getTotalPriceGoldBean()) {
            return DataResponse.error("金豆不足！");
        }

        //减除库存
        for (OrderDetailResponse detailResponse : orderResponse.getOrderDetailList()) {
            Gift gift = giftService.getGiftById(snapshotService.getGiftSnapshotById(detailResponse.getGiftSnapshotId()).getGiftId());
            if (gift != null) {
                GiftRequest giftRequest = new GiftRequest();
                BeanUtils.copyProperties(gift, giftRequest);
                giftRequest.setQuantity(detailResponse.getQuantity());
                Msg<Gift> msg = giftService.deductStockGift(giftRequest);
                if (StringUtils.isNotEmpty(msg.getErrorMsg())) {
                    return DataResponse.error(msg.getErrorMsg());
                }
            }
        }

        DataResponse dataResponse1 = feignOrderService.pay(new PayRequest() {{
            setPassword(request.getPassword());
            setBuyAmount(0L);
            setPayAmount(orderResponse.getTotalPriceGoldBean());
            setPayType(PayType.金豆.getValue());
            setTargetType(PayTarget.礼品兑换.getValue());
            setDeviceType(request.getDeviceType());
        }});

        if (dataResponse1.getRetCode() != 0) {
            //库存加回来
            for (OrderDetailResponse detailResponse : orderResponse.getOrderDetailList()) {
                Gift gift = giftService.getGiftById(snapshotService.getGiftSnapshotById(detailResponse.getGiftSnapshotId()).getGiftId());
                if (gift != null) {
                    GiftRequest giftRequest = new GiftRequest();
                    BeanUtils.copyProperties(gift, giftRequest);
                    giftRequest.setQuantity(detailResponse.getQuantity());
                    giftService.addStockGift(giftRequest);
                }
            }
            return dataResponse1;
        } else {
            //更新订单
            orderResponse.setStatus(OrderStatusEnum.待发货.getValue());
            orderService.updateOrder(orderResponse);

            orderLogService.createOrderLog(new OrderLog(){{
                setOrderId(orderResponse.getId());
                setToStatus(orderResponse.getStatus());
            }});

            //更新购物车
            CartResponse cartResponse = cartService.getCartResponseByUserId(userBaseInfo.getId());
            for(CartDetailResponse cartDetailResponse: cartResponse.getCartDetailList()){
                if(cartDetailResponse.getChecked()>0){
                    cartDetailService.deleteCartDetail(cartDetailResponse);
                }
            }
            return DataResponse.success();
        }
    }

    @LoginRequired
    @PostMapping("/setShippingAddress")
    public DataResponse setShippingAddress(@CurrentUser UserBaseInfo userBaseInfo, @RequestBody OrderRequest request) {
        if (request == null || request.getId() == null || request.getAddress() == null) {
            return DataResponse.error("参数错误！");
        }
        Address address = request.getAddress();
        if (address == null || StringUtils.isEmpty(address.getRealName()) || StringUtils.isEmpty(address.getRealName())
                || StringUtils.isEmpty(address.getMobile())) {
            return DataResponse.error("地址信息不全");
        }
        address.setUserId(userBaseInfo.getId());

        deliveryService.createDelivery(new Delivery() {{
            setOrderId(request.getId());
            setReceiverInfo(JSON.toJSONString(address));
        }});

        return DataResponse.success(address);
    }

    @LoginRequired
    @PostMapping("/filterPagedOrderResponseList")
    public DataResponse filterPagedOrderResponseList(@CurrentUser UserBaseInfo userBaseInfo, @RequestBody OrderRequest request) {
        request.setUserId(userBaseInfo.getId());
        request.setStatusList(Arrays.asList(OrderStatusEnum.待发货.getValue(),OrderStatusEnum.待签收.getValue(),
                OrderStatusEnum.已签收.getValue()));
        return DataResponse.success(orderService.filterPagedOrderResponseList(request));
    }
}
