package com.niuran.giftstore.controller;

import com.niuran.giftstore.model.Gift;
import com.niuran.giftstore.net.LoginRequired;
import com.niuran.giftstore.request.GiftRequest;
import com.niuran.giftstore.response.base.DataResponse;
import com.niuran.giftstore.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Rich on 2021-02-21 17:03
 * @Projcet giftstore
 */
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/giftController")
public class GiftController {
    @Autowired
    private GiftService giftService;

    @LoginRequired
    @PostMapping("/getGiftById")
    public DataResponse getGiftById(@RequestBody Gift gift){
        if(gift==null || gift.getId()==null){
            return DataResponse.error("参数错误！");
        }
        Gift aGift = giftService.getGiftById(gift.getId());
        if(aGift==null){
            return DataResponse.error("礼品不存在！");
        }
        return DataResponse.success(aGift);
    }

    @LoginRequired
    @PostMapping("/filterPagedGiftList")
    public DataResponse filterPagedGiftList(@RequestBody GiftRequest request){
        return DataResponse.success(giftService.filterPagedGiftList(request));
    }
}
