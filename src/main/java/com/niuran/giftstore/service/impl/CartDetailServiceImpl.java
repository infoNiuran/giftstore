package com.niuran.giftstore.service.impl;

import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.dao.CartDetailMapper;
import com.niuran.giftstore.model.Cart;
import com.niuran.giftstore.model.CartDetail;
import com.niuran.giftstore.model.CartDetailExample;
import com.niuran.giftstore.response.CartDetailResponse;
import com.niuran.giftstore.service.CartDetailService;
import com.niuran.giftstore.service.GiftService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rich on 2021-02-19 21:46
 * @Projcet giftstore
 */
@Service
public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    private CartDetailMapper cartDetailMapper;
    @Autowired
    private GiftService giftService;

    @Override
    public CartDetail getCartDetailById(Long id){
        if(id==null){
            return null;
        }
        return cartDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public CartDetail getCartDetailByGiftIdAndCartId(CartDetail cartDetail){
        CartDetailExample example = new CartDetailExample();
        example.createCriteria().andCartIdEqualTo(cartDetail.getCartId()).andGiftIdEqualTo(cartDetail.getGiftId());
        return cartDetailMapper.selectOneByExample(example);
    }

    @Override
    public CartDetailResponse getCartDetailResponseById(Long id){
        CartDetail cartDetail = getCartDetailById(id);
        if(cartDetail==null){
            return null;
        }
        CartDetailResponse response = new CartDetailResponse();
        BeanUtils.copyProperties(cartDetail,response);
        response.setGift(giftService.getGiftById(cartDetail.getGiftId()));
        return response;
    }

    @Override
    public Msg<CartDetail> createCartDetail(CartDetail cartDetail){
        if(cartDetail==null || cartDetail.getGiftId()==null){
            return Msg.error("参数错误！");
        }
        CartDetailExample example = new CartDetailExample();
        example.createCriteria().andCartIdEqualTo(cartDetail.getCartId()).andGiftIdEqualTo(cartDetail.getGiftId());
        CartDetail dbCartDetail = cartDetailMapper.selectOneByExample(example);
        if(dbCartDetail==null){
            if(cartDetail.getQuantity()==null) {
                cartDetail.setQuantity(1L);
            }
            if(cartDetail.getChecked()==null){
                cartDetail.setChecked(1);
            }
            cartDetailMapper.insertSelective(cartDetail);
        }else{
            if(cartDetail.getQuantity()==null) {
                dbCartDetail.setQuantity(dbCartDetail.getQuantity() + 1);
            }else{
                dbCartDetail.setQuantity(dbCartDetail.getQuantity()+cartDetail.getQuantity());
            }
            cartDetailMapper.updateByPrimaryKeySelective(dbCartDetail);
        }
        return Msg.success(cartDetailMapper.selectByPrimaryKey(cartDetail.getId()));
    }

    @Override
    public List<CartDetailResponse> getCartDetailResponseListByCartId(Cart cart){
        CartDetailExample example = new CartDetailExample();
        example.createCriteria().andCartIdEqualTo(cart.getId());
        example.setOrderByClause("update_time desc");

        List<CartDetail> cartDetailList = cartDetailMapper.selectByExample(example);
        List<CartDetailResponse> responseList = new ArrayList<>();

        for(CartDetail cartDetail : cartDetailList){
            CartDetailResponse response = getCartDetailResponseById(cartDetail.getId());
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public Msg<Integer> deleteCartDetail(CartDetail cartDetail){
        if(cartDetail==null || cartDetail.getId()==null){
            return Msg.error("参数错误！");
        }
        CartDetail dbCartDetail = cartDetailMapper.selectByPrimaryKey(cartDetail.getId());
        if(dbCartDetail==null){
            return Msg.error("信息不存在！");
        }
        cartDetailMapper.deleteByPrimaryKey(cartDetail.getId());
        return Msg.success(1);
    }

    @Override
    public Msg<CartDetail> updateCartDetail(CartDetail cartDetail){
        if(cartDetail==null || cartDetail.getId()==null || cartDetail.getQuantity()==null){
            return Msg.error("参数错误！");
        }
        CartDetail dbCartDetail = cartDetailMapper.selectByPrimaryKey(cartDetail.getId());
        if(dbCartDetail==null){
            return Msg.error("信息不存在！");
        }
        cartDetailMapper.updateByPrimaryKeySelective(cartDetail);
        return Msg.success(cartDetailMapper.selectByPrimaryKey(cartDetail.getId()));
    }
}
