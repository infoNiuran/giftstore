package com.niuran.giftstore.service;

import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.model.Store;

/**
 * @Author Rich on 2021-02-19 17:34
 * @Projcet giftstore
 */
public interface StoreService {
    Store getStoreById(Integer id);

    Msg<Store> createStore(Store store);

    Msg<Store> updateStore(Store store);
}
