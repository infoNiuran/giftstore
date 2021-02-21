package com.niuran.giftstore.controller.admin;

import com.niuran.giftstore.enume.DeliveryStatusEnum;
import com.niuran.giftstore.model.Delivery;
import com.niuran.giftstore.model.Order;
import com.niuran.giftstore.request.OrderRequest;
import com.niuran.giftstore.response.OrderResponse;
import com.niuran.giftstore.response.base.DataResponse;
import com.niuran.giftstore.service.DeliveryService;
import com.niuran.giftstore.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Rich on 2021-02-21 17:06
 * @Projcet giftstore
 */
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/admin/orderController")
public class AdminOrderController {

    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/deliverOrder")
    public DataResponse deliverOrder(@RequestBody Delivery delivery){
        if(delivery==null || delivery.getId()==null || StringUtils.isEmpty(delivery.getWaybill())){
            return DataResponse.error("参数错误！");
        }
        Delivery dbDelivery = deliveryService.getDeliveryById(delivery.getId());
        if(dbDelivery==null){
            return DataResponse.error("发货信息不存在！");
        }
        if(!dbDelivery.getStatus().equals(DeliveryStatusEnum.创建.getValue())){
            return DataResponse.error("当前状态为"+DeliveryStatusEnum.getEnum(delivery.getStatus()).name()+"，不能发货！");
        }
        delivery.setStatus(DeliveryStatusEnum.投递.getValue());
        deliveryService.updateDelivery(delivery);
        return DataResponse.success();
    }

    @PostMapping("/filterPagedOrderList")
    public DataResponse filterPagedOrderList(@RequestBody OrderRequest request){
        return DataResponse.success(orderService.filterPagedOrderList(request));
    }

    @PostMapping("/getOrderResponseById")
    public DataResponse getOrderResponseById(@RequestBody Order order){
        if(order==null || order.getId()==null){
            return DataResponse.error("参数错误！");
        }
        OrderResponse response = orderService.getOrderResponseById(order.getId());
        if(response==null){
            return DataResponse.error("信息不存在！");
        }
        return DataResponse.success(response);
    }

}
