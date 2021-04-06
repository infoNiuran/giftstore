package com.niuran.giftstore.service;

import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.Cart;
import com.niuran.giftstore.model.CartDetail;
import com.niuran.giftstore.response.CartDetailResponse;

import java.util.List;

/**
 * @Author Rich on 2021-02-19 21:46
 * @Projcet giftstore
 */
public interface CartDetailService {
    CartDetail getCartDetailById(Long id);

    CartDetail getCartDetailByGiftIdAndCartId(CartDetail cartDetail);

    CartDetailResponse getCartDetailResponseById(Long id);

    Msg<CartDetail> createCartDetail(CartDetail cartDetail);

    List<CartDetailResponse> getCartDetailResponseListByCartId(Cart cart);

    Msg<Integer> deleteCartDetail(CartDetail cartDetail);

    Msg<CartDetail> updateCartDetail(CartDetail cartDetail);

}
