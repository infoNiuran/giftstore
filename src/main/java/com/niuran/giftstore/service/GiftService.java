package com.niuran.giftstore.service;

import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.Gift;
import com.niuran.giftstore.request.GiftRequest;

import java.util.List;

/**
 * @Author Rich on 2021-02-19 20:00
 * @Projcet giftstore
 */
public interface GiftService {
    Gift getGiftById(Long id);

    Msg<Gift> createGift(Gift gift);

    Msg<Gift> updateGift(Gift gift);

    //上架
    Msg<Gift> shelfOnGift(Gift gift);

    //下架
    Msg<Gift> shelfOffGift(Gift gift);

    //入库，添加库存数量
    Msg<Gift> addStockGift(GiftRequest request);

    //兑换出库
    Msg<Gift> deductStockGift(GiftRequest request);

    List<Gift> searchName(String search);

    PageInfo<Gift> filterPagedGiftList(GiftRequest request);
}
