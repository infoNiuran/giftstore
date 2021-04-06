package com.niuran.giftstore.controller;

import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.bean.UserBaseInfo;
import com.niuran.giftstore.model.Cart;
import com.niuran.giftstore.model.CartDetail;
import com.niuran.giftstore.net.CurrentUser;
import com.niuran.giftstore.net.LoginRequired;
import com.niuran.giftstore.request.CartRequest;
import com.niuran.giftstore.response.CartResponse;
import com.niuran.giftstore.response.base.DataResponse;
import com.niuran.giftstore.service.CartDetailService;
import com.niuran.giftstore.service.CartService;
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
@RequestMapping("/cartController")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartDetailService cartDetailService;

    @LoginRequired
    @PostMapping("/getCartResponseById")
    public DataResponse getCartResponseById(@RequestBody Cart cart){
        if(cart==null || cart.getId()==null){
            return DataResponse.error("参数错误！");
        }
        CartResponse response = cartService.getCartResponseById(cart.getId());
        if(response==null){
            return DataResponse.error("购物车不存在！");
        }
        return DataResponse.success(response);
    }

    @LoginRequired
    @PostMapping("/getCartResponseByUserId")
    public DataResponse getCartResponseByUserId(@CurrentUser UserBaseInfo userBaseInfo){
        CartResponse response = cartService.getCartResponseByUserId(userBaseInfo.getId());
        if(response==null){
            return DataResponse.error("购物车不存在！");
        }
        return DataResponse.success(response);
    }

    @LoginRequired
    @PostMapping("/addGiftsToCart")
    public DataResponse addGiftsToCart(@CurrentUser UserBaseInfo userBaseInfo, @RequestBody CartRequest request){
        request.setUserId(userBaseInfo.getId());
        Msg<CartResponse> msg = cartService.addGiftsToCart(request);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return DataResponse.error(msg.getErrorMsg());
        }
        return DataResponse.success(msg.getData());
    }

    @LoginRequired
    @PostMapping("/removeGiftsFromCart")
    public DataResponse removeGiftsFromCart(@CurrentUser UserBaseInfo userBaseInfo, @RequestBody CartRequest request){
        request.setUserId(userBaseInfo.getId());
        Msg<CartResponse> msg = cartService.removeGiftsFromCart(request);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return DataResponse.error(msg.getErrorMsg());
        }
        return DataResponse.success(msg.getData());
    }

    @LoginRequired
    @PostMapping("/updateCartComplete")
    public DataResponse updateCartComplete(@CurrentUser UserBaseInfo userBaseInfo, @RequestBody CartRequest request){
        request.setUserId(userBaseInfo.getId());
        Msg<CartResponse> msg = cartService.updateCartComplete(request);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return DataResponse.error(msg.getErrorMsg());
        }
        return DataResponse.success(msg.getData());
    }

    @LoginRequired
    @PostMapping("/updateCartDetail")
    public DataResponse increaseCartDetail(@RequestBody CartDetail cartDetail){
        Msg<CartDetail> msg = cartDetailService.updateCartDetail(cartDetail);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return DataResponse.error(msg.getErrorMsg());
        }
        return DataResponse.success();
    }
}
