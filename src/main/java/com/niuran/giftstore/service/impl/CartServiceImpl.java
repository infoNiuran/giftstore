package com.niuran.giftstore.service.impl;

import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.dao.CartMapper;
import com.niuran.giftstore.model.Cart;
import com.niuran.giftstore.model.CartDetail;
import com.niuran.giftstore.model.CartExample;
import com.niuran.giftstore.request.CartRequest;
import com.niuran.giftstore.request.GiftRequest;
import com.niuran.giftstore.response.CartDetailResponse;
import com.niuran.giftstore.response.CartResponse;
import com.niuran.giftstore.service.CartDetailService;
import com.niuran.giftstore.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Rich on 2021-02-19 21:37
 * @Projcet giftstore
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartDetailService cartDetailService;

    @Override
    public Cart getCartById(Long id) {
        if (id == null) {
            return null;
        }
        return cartMapper.selectByPrimaryKey(id);
    }

    @Override
    public CartResponse getCartResponseByUserId(Long userId) {
        CartExample example = new CartExample();
        example.createCriteria().andUserIdEqualTo(userId);
        Cart cart = cartMapper.selectOneByExample(example);

        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            Msg<Cart> msg = createCart(cart);
            if(StringUtils.isNotEmpty(msg.getErrorMsg())){
                return null;
            }else{
                cart = msg.getData();
            }
        }
        return getCartResponseById(cart.getId());
    }

    @Override
    public CartResponse getCartResponseById(Long id) {
        Cart cart = getCartById(id);
        if (cart == null) {
            return null;
        }
        CartResponse response = new CartResponse();
        BeanUtils.copyProperties(cart, response);

        response.setCartDetailList(cartDetailService.getCartDetailResponseListByCartId(cart));
        return response;
    }

    @Override
    public Msg<Cart> createCart(Cart cart) {
        if (cart == null || cart.getUserId() == null) {
            return Msg.error("参数错误！");
        }

        CartExample example = new CartExample();
        example.createCriteria().andUserIdEqualTo(cart.getUserId());
        Cart dbCart = cartMapper.selectOneByExample(example);

        if (dbCart != null) {
            return Msg.success(dbCart);
        }

        cartMapper.insertSelective(cart);

        return Msg.success(cartMapper.selectByPrimaryKey(cart.getId()));
    }

    @Override
    public Msg<Cart> updateCart(Cart cart) {
        if (cart == null || cart.getId() == null) {
            return Msg.error("参数错误！");
        }

        Cart dbCart = cartMapper.selectByPrimaryKey(cart.getId());
        if (dbCart == null) {
            return createCart(cart);
        }

        if (cart.getUserId() != null) {
            CartExample example = new CartExample();
            example.createCriteria().andUserIdEqualTo(cart.getUserId()).andIdNotEqualTo(dbCart.getId());
            if (cartMapper.countByExample(example) > 0) {
                return Msg.error("购物车重复！");
            }
        }

        cartMapper.updateByPrimaryKeySelective(cart);
        return Msg.success(cartMapper.selectByPrimaryKey(cart.getId()));
    }

    @Override
    public Msg<Cart> emptyCart(Cart cart) {
        if (cart == null || cart.getId() == null) {
            return Msg.error("参数错误！");
        }
        Cart dbCart = cartMapper.selectByPrimaryKey(cart.getId());
        if (dbCart == null) {
            return Msg.error("购物车不存在！");
        }
        List<CartDetailResponse> cartDetailResponseList = cartDetailService.getCartDetailResponseListByCartId(cart);
        if (cartDetailResponseList.size() > 0) {
            for (CartDetailResponse response : cartDetailResponseList) {
                CartDetail cartDetail = new CartDetail();
                BeanUtils.copyProperties(response, cartDetail);
                cartDetailService.deleteCartDetail(cartDetail);
            }
        }
        return Msg.success(dbCart);
    }

    @Override
    public Msg<CartResponse> updateCartComplete(CartRequest request) {
        if (request == null || request.getUserId() == null) {
            return Msg.error("参数错误！");
        }
        CartResponse cartResponse = getCartResponseByUserId(request.getUserId());
        Cart dbCart;
        if (cartResponse == null) {
            Msg<Cart> cartMsg = createCart(new Cart() {{
                setUserId(request.getUserId());
            }});
            if (StringUtils.isNotEmpty(cartMsg.getErrorMsg())) {
                return Msg.error(cartMsg.getErrorMsg());
            }
            dbCart = cartMsg.getData();
        } else {
            dbCart = cartMapper.selectByPrimaryKey(cartResponse.getId());
        }

        //清空购物车
        emptyCart(dbCart);

        //添加新的礼品
        if (request.getCartDetailList() != null && request.getCartDetailList().size() > 0) {
            for (CartDetail cartDetail : request.getCartDetailList()) {
                cartDetailService.createCartDetail(cartDetail);
            }
        }
        return Msg.success(getCartResponseById(dbCart.getId()));
    }

    @Override
    public Msg<CartResponse> addGiftsToCart(CartRequest request) {
        if (request == null || request.getUserId() == null || request.getGiftList() == null) {
            return Msg.error("参数错误！");
        }
        CartResponse cartResponse = getCartResponseByUserId(request.getUserId());
        Cart cart = new Cart();
        if (cartResponse == null) {
            cart.setUserId(request.getUserId());
            Msg<Cart> msg = createCart(cart);
            cart = msg.getData();
        } else {
            BeanUtils.copyProperties(cartResponse, cart);
        }

        for (GiftRequest giftRequest : request.getGiftList()) {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setCartId(cart.getId());
            cartDetail.setQuantity(giftRequest.getQuantity());
            cartDetail.setGiftId(giftRequest.getId());
            Msg<CartDetail> msg = cartDetailService.createCartDetail(cartDetail);
            if (StringUtils.isNotEmpty(msg.getErrorMsg())) {
                return Msg.error(msg.getErrorMsg());
            }
        }

        return Msg.success(getCartResponseById(cart.getId()));
    }

    @Override
    public Msg<CartResponse> removeGiftsFromCart(CartRequest request) {
        if (request == null || request.getUserId() == null || request.getCartDetailList() == null) {
            return Msg.error("参数错误！");
        }
        CartResponse cartResponse = getCartResponseByUserId(request.getUserId());

        List<CartDetail> cartDetailList = request.getCartDetailList();

        for (CartDetail cartDetail : cartDetailList) {
            cartDetail.setCartId(cartResponse.getId());
            cartDetailService.deleteCartDetail(cartDetail);
        }
        return Msg.success(getCartResponseById(cartResponse.getId()));
    }
}
