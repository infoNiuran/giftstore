package com.niuran.giftstore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.dao.GiftMapper;
import com.niuran.giftstore.enume.PurchaseOptionEnum;
import com.niuran.giftstore.enume.ShipOptionEnum;
import com.niuran.giftstore.enume.StatusEnum;
import com.niuran.giftstore.model.Gift;
import com.niuran.giftstore.model.GiftActionRecord;
import com.niuran.giftstore.model.GiftExample;
import com.niuran.giftstore.request.GiftRequest;
import com.niuran.giftstore.response.GiftResponse;
import com.niuran.giftstore.service.GiftActionRecordService;
import com.niuran.giftstore.service.GiftService;
import com.niuran.giftstore.service.OrderDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rich on 2021-02-19 20:00
 * @Projcet giftstore
 */
@Service
public class GiftServiceImpl implements GiftService {
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private GiftActionRecordService giftActionRecordService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public Gift getGiftById(Long id) {
        if (id == null) {
            return null;
        }
        return giftMapper.selectByPrimaryKey(id);
    }


    @Override
    public GiftResponse getGiftResponseById(Long id) {
        Gift gift = getGiftById(id);
        if (gift == null) {
            return null;
        }
        GiftResponse response = new GiftResponse();
        BeanUtils.copyProperties(gift,response);
        response.setSalesVolume(orderDetailService.salesVolumeOfGift(id));
        return response;
    }

    @Override
    public Msg<Gift> createGift(Gift gift) {
        if (gift == null || StringUtils.isEmpty(gift.getName()) || StringUtils.isEmpty(gift.getUnit())
                || (gift.getUnit() == null && gift.getUnitPriceGoldBean() == null)
                || StringUtils.isEmpty(gift.getImages()) || gift.getImages().equals("[]")) {
            return Msg.error("???????????????");
        }

        //?????????
        gift.setCode(com.niuran.giftstore.util.StringUtils.randomGiftCode());
        if(gift.getPurchaseOption()==null) {
            gift.setPurchaseOption(PurchaseOptionEnum.??????.getValue());
        }
        if(gift.getShipOption()==null){
            gift.setShipOption(ShipOptionEnum.?????????.getValue());
        }
        if(gift.getStatus()==null){
            gift.setStatus(StatusEnum.??????.getValue());
        }

        giftMapper.insertSelective(gift);

        //?????????

        return Msg.success(giftMapper.selectByPrimaryKey(gift.getId()));
    }

    @Override
    public Msg<Gift> updateGift(Gift gift){
        if(gift==null || gift.getId()==null){
            return Msg.error("???????????????");
        }
        Gift dbGift = giftMapper.selectByPrimaryKey(gift.getId());
        if(dbGift==null){
            return Msg.error("??????????????????");
        }

        if(StringUtils.isNotEmpty(gift.getCode())){
            GiftExample example = new GiftExample();
            example.createCriteria().andCodeEqualTo(gift.getCode()).andIdNotEqualTo(gift.getId());
            if(giftMapper.countByExample(example)>0){
                return Msg.error("?????????????????????");
            }
        }
        giftMapper.updateByPrimaryKeySelective(gift);

        return Msg.success(giftMapper.selectByPrimaryKey(gift.getId()));
    }

    //??????
    @Override
    public Msg<Gift> shelfOnGift(Gift gift){
        gift.setStatus(StatusEnum.??????.getValue());
        Msg<Gift> msg = updateGift(gift);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return msg;
        }
        GiftActionRecord record = new GiftActionRecord();
        record.setGiftId(gift.getId());
        record.setAction("??????");
        giftActionRecordService.createGiftActionRecord(record);
        return Msg.success(giftMapper.selectByPrimaryKey(gift.getId()));
    }

    //??????
    @Override
    public Msg<Gift> shelfOffGift(Gift gift){
        gift.setStatus(StatusEnum.?????????.getValue());
        Msg<Gift> msg = updateGift(gift);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return msg;
        }
        GiftActionRecord record = new GiftActionRecord();
        record.setGiftId(gift.getId());
        record.setAction("??????");
        giftActionRecordService.createGiftActionRecord(record);
        return Msg.success(giftMapper.selectByPrimaryKey(gift.getId()));
    }

    //???????????????????????????
    @Override
    public Msg<Gift> addStockGift(GiftRequest request){
        if(request==null || request.getId()==null || request.getQuantity()==null || request.getQuantity()<0){
            return Msg.error("???????????????");
        }
        Gift dbGift = giftMapper.selectByPrimaryKey(request.getId());
        if(dbGift==null){
            return Msg.error("??????????????????");
        }
        dbGift.setStock(dbGift.getStock()+request.getQuantity());

        Msg<Gift> msg = updateGift(dbGift);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return msg;
        }
        GiftActionRecord record = new GiftActionRecord();
        record.setGiftId(request.getId());
        record.setAction("??????");
        record.setQuantity(request.getQuantity());
        giftActionRecordService.createGiftActionRecord(record);
        return Msg.success(giftMapper.selectByPrimaryKey(dbGift.getId()));
    }

    //????????????
    @Override
    public Msg<Gift> deductStockGift(GiftRequest request){
        if(request==null || request.getId()==null || request.getQuantity()==null || request.getQuantity()<0){
            return Msg.error("???????????????");
        }
        Gift dbGift = giftMapper.selectByPrimaryKey(request.getId());
        if(dbGift==null){
            return Msg.error("??????????????????");
        }
        if(dbGift.getStock()>request.getQuantity()){
            dbGift.setStock(dbGift.getStock()-request.getQuantity());
        }else{
            return Msg.error("???????????????");
        }

        Msg<Gift> msg = updateGift(dbGift);
        if(StringUtils.isNotEmpty(msg.getErrorMsg())){
            return msg;
        }
        GiftActionRecord record = new GiftActionRecord();
        record.setGiftId(request.getId());
        record.setAction("??????");
        record.setQuantity(request.getQuantity());
        giftActionRecordService.createGiftActionRecord(record);
        return Msg.success(giftMapper.selectByPrimaryKey(request.getId()));
    }

    @Override
    public List<Gift> searchName(String search) {
        List<Gift> giftList = new ArrayList<>();
        if(StringUtils.isEmpty(search)){
            return giftList;
        }
        GiftExample example = new GiftExample();
        example.createCriteria().andNameLike("%"+search+"%");
        return giftMapper.selectByExample(example);
    }

    @Override
    public PageInfo<GiftResponse> filterPagedGiftList(GiftRequest request){
        GiftExample example = new GiftExample();
        GiftExample.Criteria criteria = example.createCriteria();

        if(request.getId()!=null){
            criteria.andIdEqualTo(request.getId());
        }
        if(StringUtils.isNotEmpty(request.getCode())){
            criteria.andCodeEqualTo(request.getCode());
        }
        if(StringUtils.isNotEmpty(request.getName())){
            criteria.andNameEqualTo(request.getName());
        }
        if(StringUtils.isNotEmpty(request.getSearch())){
            criteria.andNameLike("%"+request.getSearch()+"%");
        }
        if(request.getPurchaseOption()!=null){
            criteria.andPurchaseOptionEqualTo(request.getPurchaseOption());
        }
        if(request.getShipOption()!=null){
            criteria.andShipOptionEqualTo(request.getShipOption());
        }
        if(request.getStatus()!=null){
            criteria.andStatusEqualTo(request.getStatus());
        }
        if(request.getStartTime()!=null){
            criteria.andUpdateTimeGreaterThan(request.getStartTime());
        }
        if(request.getEndTime()!=null){
            criteria.andUpdateTimeLessThanOrEqualTo(request.getEndTime());
        }
        if(StringUtils.isNotEmpty(request.getOrderClause())){
            example.setOrderByClause(request.getOrderClause());
        }else{
            example.setOrderByClause("sort asc");
        }

        PageHelper.startPage(request.getPage(),request.getSize());
        PageInfo<Gift> pageInfo = new PageInfo<>(giftMapper.selectByExample(example));

        PageInfo<GiftResponse> responsePageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,responsePageInfo);

        List<GiftResponse> responseList = new ArrayList<>();
        for(Gift gift: pageInfo.getList()){
            GiftResponse response = getGiftResponseById(gift.getId());
            responseList.add(response);
        }

        responsePageInfo.setList(responseList);

        return responsePageInfo;
    }
}
