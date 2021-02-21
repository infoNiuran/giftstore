package com.niuran.giftstore.service;

import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.GiftActionRecord;
import com.niuran.giftstore.request.GiftActionRecordRequest;

/**
 * @Author Rich on 2021-02-19 20:33
 * @Projcet giftstore
 */
public interface GiftActionRecordService {
    GiftActionRecord getGiftActionRecordById(Long id);

    Msg<GiftActionRecord> createGiftActionRecord(GiftActionRecord giftActionRecord);

    PageInfo<GiftActionRecord> filterPagedGiftActionRecordList(GiftActionRecordRequest request);
}
