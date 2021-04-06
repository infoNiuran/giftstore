package com.niuran.giftstore.controller;

import com.niuran.giftstore.response.base.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Rich on 2021-04-06 17:32
 * @Projcet giftstore
 */
@RestController
@RequestMapping("/storeController")
public class StoreController {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @PostMapping("/getStoreStatus")
    public DataResponse getStoreStatus(){
        String status = redisTemplate.opsForValue().get("giftStoreApi:giftStoreStatus");
        if(status!=null && status.equals("1")){
            return DataResponse.success("1");
        }
        return DataResponse.success("0");
    }
}
