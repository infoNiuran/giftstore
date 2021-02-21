package com.niuran.giftstore.feign;

import com.niuran.giftstore.response.base.DataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


/**
 * @Author MrD on 2018/8/1.
 */
@FeignClient(value = "CLOUD-USERINFO-SERVICE")
@Service
public interface FeignUserInfoService {

    /**
     * 获取登录状态
     * 已登录返回用户信息
     */
    @RequestMapping(value = "/userInfoController/getUserInfo", method = RequestMethod.POST)
    DataResponse getLoginStatus();

    @RequestMapping(value = "/crmUserController/getUserAccountInfoByUserId", method = RequestMethod.GET)
    DataResponse getUserAccountInfoByUserId(@RequestParam(value = "userId") Long userId);

    @PostMapping(value = "/crmUserController/getUserBaseInfo")
    DataResponse getUserBaseInfo(@RequestParam(value = "userId") Long userId);

    @RequestMapping(value = "/userInfoController/getUserInfoDetailsByUserId", method = RequestMethod.POST)
    DataResponse getUserInfoDetailsByUserId(@RequestParam("userId") Long userId, @RequestParam("hidePhone") Boolean hidePhone, @RequestParam("withCardNo") Boolean withCardNo);

    @GetMapping("/userWxInfoController/getUserWxInfoByOpenId")
    DataResponse getUserWxInfoByOpenId(@RequestParam(value = "openId") String openId, @RequestParam(value = "appType") Integer appType);

//    @PostMapping("/userWxInfoController/getUserWxInfoByUserId")
//    DataResponse getUserWxInfoByUserId(@RequestBody UserWxInfo userWxInfo);
}
