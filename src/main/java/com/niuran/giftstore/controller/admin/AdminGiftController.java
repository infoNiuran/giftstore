package com.niuran.giftstore.controller.admin;

import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.Gift;
import com.niuran.giftstore.request.GiftRequest;
import com.niuran.giftstore.response.base.DataResponse;
import com.niuran.giftstore.service.GiftService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/admin/giftController")
public class AdminGiftController {

    @Autowired
    private GiftService giftService;

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

    @PostMapping("/createGift")
    public DataResponse createGift(@RequestBody Gift gift){
        Msg<Gift> msg = giftService.createGift(gift);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return DataResponse.error(msg.getErrorMsg());
        }
        return DataResponse.success(msg.getData());
    }

    @PostMapping("/shelfOnGift")
    public DataResponse shelfOnGift(@RequestBody Gift gift){
        Msg<Gift> msg = giftService.shelfOnGift(gift);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return DataResponse.error(msg.getErrorMsg());
        }
        return DataResponse.success(msg.getData());
    }

    @PostMapping("/shelfOffGift")
    public DataResponse shelfOffGift(@RequestBody Gift gift){
        Msg<Gift> msg = giftService.shelfOffGift(gift);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return DataResponse.error(msg.getErrorMsg());
        }
        return DataResponse.success(msg.getData());
    }

    @PostMapping("/filterPagedGiftList")
    public DataResponse filterPagedGiftList(@RequestBody GiftRequest request){
        return DataResponse.success(giftService.filterPagedGiftList(request));
    }

    @PostMapping("/addStockGift")
    public DataResponse addStockGift(@RequestBody GiftRequest request){
        Msg<Gift> msg = giftService.addStockGift(request);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return DataResponse.error(msg.getErrorMsg());
        }
        return DataResponse.success(msg.getData());
    }

    @PostMapping("/deductStockGift")
    public DataResponse deductStockGift(@RequestBody GiftRequest request){
        Msg<Gift> msg = giftService.deductStockGift(request);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return DataResponse.error(msg.getErrorMsg());
        }
        return DataResponse.success(msg.getData());
    }
}
