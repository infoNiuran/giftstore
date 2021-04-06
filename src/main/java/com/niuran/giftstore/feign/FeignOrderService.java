package com.niuran.giftstore.feign;

import com.niuran.giftstore.bean.PayRequest;
import com.niuran.giftstore.response.base.DataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


/**
 * @Author MrD on 2018/8/1.
 */
@FeignClient(value = "CLOUD-ORDERINFO-SERVICE")
@Service
public interface FeignOrderService {
    @RequestMapping(value = "/payController/pay", method = RequestMethod.POST)
    DataResponse pay(@RequestBody PayRequest payRequest);
}
