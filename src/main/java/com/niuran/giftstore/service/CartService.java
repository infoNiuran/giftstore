package com.niuran.giftstore.service;

import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.Cart;
import com.niuran.giftstore.request.CartRequest;
import com.niuran.giftstore.request.GiftRequest;
import com.niuran.giftstore.response.CartResponse;

/**
 * @Author Rich on 2021-02-19 21:37
 * @Projcet giftstore
 */
public interface CartService {
    Cart getCartById(Long id);

    CartResponse getCartResponseByUserId(Long userId);

    CartResponse getCartResponseById(Long id);

    Msg<Cart> createCart(Cart cart);

    Msg<Cart> updateCart(Cart cart);

    Msg<Cart> emptyCart(Cart cart);

    Msg<CartResponse> updateCartComplete(CartRequest request);

    Msg<CartResponse> addGiftsToCart(CartRequest request);

    Msg<CartResponse> removeGiftsFromCart(CartRequest request);
}
