package com.niuran.giftstore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.dao.GiftActionRecordMapper;
import com.niuran.giftstore.model.GiftActionRecord;
import com.niuran.giftstore.model.GiftActionRecordExample;
import com.niuran.giftstore.request.GiftActionRecordRequest;
import com.niuran.giftstore.service.GiftActionRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Rich on 2021-02-19 20:34
 * @Projcet giftstore
 */
@Service
public class GiftActionRecordServiceImpl implements GiftActionRecordService {

    @Autowired
    private GiftActionRecordMapper giftActionRecordMapper;

    @Override
    public GiftActionRecord getGiftActionRecordById(Long id){
        if (id == null) {
            return null;
        }
        return giftActionRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public Msg<GiftActionRecord> createGiftActionRecord(GiftActionRecord giftActionRecord){
        if(giftActionRecord==null || StringUtils.isEmpty(giftActionRecord.getAction()) || giftActionRecord.getGiftId()==null){
            return Msg.error("参数错误！");
        }
        giftActionRecordMapper.insertSelective(giftActionRecord);
        return Msg.success(giftActionRecordMapper.selectByPrimaryKey(giftActionRecord.getId()));
    }

    @Override
    public PageInfo<GiftActionRecord> filterPagedGiftActionRecordList(GiftActionRecordRequest request){
        GiftActionRecordExample example = new GiftActionRecordExample();
        GiftActionRecordExample.Criteria criteria = example.createCriteria();

        if(request.getId()!=null){
            criteria.andIdEqualTo(request.getId());
        }
        if(request.getGiftId()!=null){
            criteria.andGiftIdEqualTo(request.getGiftId());
        }
        if(StringUtils.isNotEmpty(request.getAction())){
            criteria.andActionEqualTo(request.getAction());
        }
        if(request.getOperator()!=null){
            criteria.andOperatorEqualTo(request.getOperator());
        }
        if(request.getStartTime()!=null){
            criteria.andCreateTimeGreaterThanOrEqualTo(request.getStartTime());
        }
        if(request.getEndTime()!=null){
            criteria.andCreateTimeLessThanOrEqualTo(request.getEndTime());
        }
        if(StringUtils.isNotEmpty(request.getOrderClause())){
            example.setOrderByClause(request.getOrderClause());
        }else{
            example.setOrderByClause("create_time desc");
        }

        PageHelper.startPage(request.getPage(),request.getSize());
        return new PageInfo<>(giftActionRecordMapper.selectByExample(example));
    }
}
