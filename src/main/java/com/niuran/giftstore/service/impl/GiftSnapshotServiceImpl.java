package com.niuran.giftstore.service.impl;

import com.niuran.giftstore.bean.Msg;
import com.niuran.giftstore.dao.GiftSnapshotMapper;
import com.niuran.giftstore.enume.StatusEnum;
import com.niuran.giftstore.model.Gift;
import com.niuran.giftstore.model.GiftSnapshot;
import com.niuran.giftstore.model.GiftSnapshotExample;
import com.niuran.giftstore.service.GiftService;
import com.niuran.giftstore.service.GiftSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rich on 2021-02-19 20:47
 * @Projcet giftstore
 */
@Service
public class GiftSnapshotServiceImpl implements GiftSnapshotService {
    @Autowired
    private GiftSnapshotMapper giftSnapshotMapper;
    @Autowired
    private GiftService giftService;

    @Override
    public GiftSnapshot getGiftSnapshotById(Long id) {
        if (id == null) {
            return null;
        }
        return giftSnapshotMapper.selectByPrimaryKey(id);
    }

    @Override
    public Msg<GiftSnapshot> createGiftSnapshot(GiftSnapshot giftSnapshot) {
        if (giftSnapshot == null || giftSnapshot.getGiftId() == null) {
            return Msg.error("参数错误！");
        }

        Gift gift = giftService.getGiftById(giftSnapshot.getGiftId());

        if (gift == null) {
            return Msg.error("礼品不存在！");
        }
        if (gift.getStatus().equals(StatusEnum.不可用.getValue())) {
            return Msg.error("礼品已下架！");
        }

        //看看有没有变化
        //看最近的一次snapshot
        boolean changed =false;

        GiftSnapshotExample example = new GiftSnapshotExample();
        example.createCriteria().andGiftIdEqualTo(giftSnapshot.getGiftId());
        example.setOrderByClause("create_time desc");
        GiftSnapshot lastSnapshot = giftSnapshotMapper.selectOneByExample(example);

        if (lastSnapshot != null) {
            if (!lastSnapshot.getName().equals(gift.getName())
                    || !lastSnapshot.getDescription().equals(gift.getDescription())
                    || !lastSnapshot.getImages().equals(gift.getImages())
                    || !lastSnapshot.getPurchaseOption().equals(gift.getPurchaseOption())
                    || !lastSnapshot.getShipOption().equals(giftSnapshot.getShipOption())
                    || !lastSnapshot.getUnit().equals(gift.getUnit())
                    || !lastSnapshot.getUnitPrice().equals(gift.getUnitPrice())
                    || lastSnapshot.getUnitPriceGoldBean().equals(gift.getUnitPriceGoldBean())){
                changed = true;
            }
        }else{
            changed = true;
        }

        if(changed){
            giftSnapshotMapper.insertSelective(giftSnapshot);
            return Msg.success(giftSnapshotMapper.selectByPrimaryKey(giftSnapshot.getId()));
        }else{
            return Msg.success(lastSnapshot);
        }
    }

    @Override
    public List<GiftSnapshot> searchGift(List<Long> giftIdList) {
        List<GiftSnapshot> snapshotList = new ArrayList<>();
        if(giftIdList.size()==0){
            return snapshotList;
        }
        GiftSnapshotExample example = new GiftSnapshotExample();
        GiftSnapshotExample.Criteria criteria = example.createCriteria();
        criteria.andGiftIdIn(giftIdList);
        return giftSnapshotMapper.selectByExample(example);
    }
}
