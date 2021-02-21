package com.niuran.giftstore.controller.admin;

import com.niuran.giftstore.model.Delivery;
import com.niuran.giftstore.request.DeliveryRequest;
import com.niuran.giftstore.response.base.DataResponse;
import com.niuran.giftstore.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Rich on 2021-02-21 19:28
 * @Projcet giftstore
 */
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/admin/deliveryController")
public class AdminDeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/getDeliveryById")
    public DataResponse getDeliveryById(@RequestBody Delivery delivery){
        if(delivery==null || delivery.getId()==null){
            return DataResponse.error("参数错误！");
        }
        Delivery dbDelivery = deliveryService.getDeliveryById(delivery.getId());
        if(dbDelivery==null){
            return DataResponse.error("信息不存在！");
        }
        return DataResponse.success(dbDelivery);
    }

    @PostMapping("/getDeliveryListByOrderId")
    public DataResponse getDeliveryListByOrderId(@RequestBody Delivery delivery){
        if(delivery==null || delivery.getOrderId()==null){
            return DataResponse.error("参数错误！");
        }
        return DataResponse.success(deliveryService.getDeliveryListByOrderId(delivery.getOrderId()));
    }

    @PostMapping("/filterPagedDeliveryList")
    public DataResponse filterPagedDeliveryList(@RequestBody DeliveryRequest request){
        return DataResponse.success(deliveryService.filterPagedDeliveryList(request));
    }
}
