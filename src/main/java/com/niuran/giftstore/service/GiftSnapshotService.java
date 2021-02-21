package com.niuran.giftstore.service;

import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.GiftSnapshot;

import java.util.List;

/**
 * @Author Rich on 2021-02-19 20:47
 * @Projcet giftstore
 */
public interface GiftSnapshotService {
    GiftSnapshot getGiftSnapshotById(Long id);

    Msg<GiftSnapshot> createGiftSnapshot(GiftSnapshot giftSnapshot);

    List<GiftSnapshot> searchGift(List<Long> giftIdList);
}
