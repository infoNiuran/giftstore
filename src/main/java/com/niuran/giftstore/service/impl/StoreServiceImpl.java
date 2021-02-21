package com.niuran.giftstore.service.impl;

import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.dao.StoreMapper;
import com.niuran.giftstore.enume.StatusEnum;
import com.niuran.giftstore.model.Store;
import com.niuran.giftstore.model.StoreExample;
import com.niuran.giftstore.service.StoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Rich on 2021-02-19 17:35
 * @Projcet giftstore
 */
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public Store getStoreById(Integer id){
        if(id==null){
            return null;
        }
        return storeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Msg<Store> createStore(Store store){
        if(store==null || StringUtils.isEmpty(store.getName())){
            return Msg.error("参数错误！");
        }

        StoreExample example = new StoreExample();
        example.createCriteria().andNameEqualTo(store.getName());
        if(storeMapper.countByExample(example)>0){
            return Msg.error("名称重复！");
        }

        store.setStatus(StatusEnum.可用.getValue());
        storeMapper.insertSelective(store);
        return Msg.success(storeMapper.selectByPrimaryKey(store.getId()));
    }

    @Override
    public Msg<Store> updateStore(Store store){
        if(store==null || store.getId()==null){
            return Msg.error("参数错误！");
        }
        Store dbStore = storeMapper.selectByPrimaryKey(store.getId());
        if(dbStore==null){
            return Msg.error("信息不存在！");
        }

        storeMapper.updateByPrimaryKeySelective(store);

        return Msg.success(storeMapper.selectByPrimaryKey(store.getId()));
    }
}
