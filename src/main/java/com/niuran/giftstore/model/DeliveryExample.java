package com.niuran.giftstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeliveryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeliveryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCarrierIdIsNull() {
            addCriterion("carrier_id is null");
            return (Criteria) this;
        }

        public Criteria andCarrierIdIsNotNull() {
            addCriterion("carrier_id is not null");
            return (Criteria) this;
        }

        public Criteria andCarrierIdEqualTo(Long value) {
            addCriterion("carrier_id =", value, "carrierId");
            return (Criteria) this;
        }

        public Criteria andCarrierIdNotEqualTo(Long value) {
            addCriterion("carrier_id <>", value, "carrierId");
            return (Criteria) this;
        }

        public Criteria andCarrierIdGreaterThan(Long value) {
            addCriterion("carrier_id >", value, "carrierId");
            return (Criteria) this;
        }

        public Criteria andCarrierIdGreaterThanOrEqualTo(Long value) {
            addCriterion("carrier_id >=", value, "carrierId");
            return (Criteria) this;
        }

        public Criteria andCarrierIdLessThan(Long value) {
            addCriterion("carrier_id <", value, "carrierId");
            return (Criteria) this;
        }

        public Criteria andCarrierIdLessThanOrEqualTo(Long value) {
            addCriterion("carrier_id <=", value, "carrierId");
            return (Criteria) this;
        }

        public Criteria andCarrierIdIn(List<Long> values) {
            addCriterion("carrier_id in", values, "carrierId");
            return (Criteria) this;
        }

        public Criteria andCarrierIdNotIn(List<Long> values) {
            addCriterion("carrier_id not in", values, "carrierId");
            return (Criteria) this;
        }

        public Criteria andCarrierIdBetween(Long value1, Long value2) {
            addCriterion("carrier_id between", value1, value2, "carrierId");
            return (Criteria) this;
        }

        public Criteria andCarrierIdNotBetween(Long value1, Long value2) {
            addCriterion("carrier_id not between", value1, value2, "carrierId");
            return (Criteria) this;
        }

        public Criteria andCarrierNameIsNull() {
            addCriterion("carrier_name is null");
            return (Criteria) this;
        }

        public Criteria andCarrierNameIsNotNull() {
            addCriterion("carrier_name is not null");
            return (Criteria) this;
        }

        public Criteria andCarrierNameEqualTo(String value) {
            addCriterion("carrier_name =", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameNotEqualTo(String value) {
            addCriterion("carrier_name <>", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameGreaterThan(String value) {
            addCriterion("carrier_name >", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameGreaterThanOrEqualTo(String value) {
            addCriterion("carrier_name >=", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameLessThan(String value) {
            addCriterion("carrier_name <", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameLessThanOrEqualTo(String value) {
            addCriterion("carrier_name <=", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameLike(String value) {
            addCriterion("carrier_name like", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameNotLike(String value) {
            addCriterion("carrier_name not like", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameIn(List<String> values) {
            addCriterion("carrier_name in", values, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameNotIn(List<String> values) {
            addCriterion("carrier_name not in", values, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameBetween(String value1, String value2) {
            addCriterion("carrier_name between", value1, value2, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameNotBetween(String value1, String value2) {
            addCriterion("carrier_name not between", value1, value2, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCostIsNull() {
            addCriterion("cost is null");
            return (Criteria) this;
        }

        public Criteria andCostIsNotNull() {
            addCriterion("cost is not null");
            return (Criteria) this;
        }

        public Criteria andCostEqualTo(Long value) {
            addCriterion("cost =", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotEqualTo(Long value) {
            addCriterion("cost <>", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThan(Long value) {
            addCriterion("cost >", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThanOrEqualTo(Long value) {
            addCriterion("cost >=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThan(Long value) {
            addCriterion("cost <", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThanOrEqualTo(Long value) {
            addCriterion("cost <=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostIn(List<Long> values) {
            addCriterion("cost in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotIn(List<Long> values) {
            addCriterion("cost not in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostBetween(Long value1, Long value2) {
            addCriterion("cost between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotBetween(Long value1, Long value2) {
            addCriterion("cost not between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andShipTimeIsNull() {
            addCriterion("ship_time is null");
            return (Criteria) this;
        }

        public Criteria andShipTimeIsNotNull() {
            addCriterion("ship_time is not null");
            return (Criteria) this;
        }

        public Criteria andShipTimeEqualTo(Date value) {
            addCriterion("ship_time =", value, "shipTime");
            return (Criteria) this;
        }

        public Criteria andShipTimeNotEqualTo(Date value) {
            addCriterion("ship_time <>", value, "shipTime");
            return (Criteria) this;
        }

        public Criteria andShipTimeGreaterThan(Date value) {
            addCriterion("ship_time >", value, "shipTime");
            return (Criteria) this;
        }

        public Criteria andShipTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ship_time >=", value, "shipTime");
            return (Criteria) this;
        }

        public Criteria andShipTimeLessThan(Date value) {
            addCriterion("ship_time <", value, "shipTime");
            return (Criteria) this;
        }

        public Criteria andShipTimeLessThanOrEqualTo(Date value) {
            addCriterion("ship_time <=", value, "shipTime");
            return (Criteria) this;
        }

        public Criteria andShipTimeIn(List<Date> values) {
            addCriterion("ship_time in", values, "shipTime");
            return (Criteria) this;
        }

        public Criteria andShipTimeNotIn(List<Date> values) {
            addCriterion("ship_time not in", values, "shipTime");
            return (Criteria) this;
        }

        public Criteria andShipTimeBetween(Date value1, Date value2) {
            addCriterion("ship_time between", value1, value2, "shipTime");
            return (Criteria) this;
        }

        public Criteria andShipTimeNotBetween(Date value1, Date value2) {
            addCriterion("ship_time not between", value1, value2, "shipTime");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeIsNull() {
            addCriterion("arrived_time is null");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeIsNotNull() {
            addCriterion("arrived_time is not null");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeEqualTo(Date value) {
            addCriterion("arrived_time =", value, "arrivedTime");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeNotEqualTo(Date value) {
            addCriterion("arrived_time <>", value, "arrivedTime");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeGreaterThan(Date value) {
            addCriterion("arrived_time >", value, "arrivedTime");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("arrived_time >=", value, "arrivedTime");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeLessThan(Date value) {
            addCriterion("arrived_time <", value, "arrivedTime");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeLessThanOrEqualTo(Date value) {
            addCriterion("arrived_time <=", value, "arrivedTime");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeIn(List<Date> values) {
            addCriterion("arrived_time in", values, "arrivedTime");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeNotIn(List<Date> values) {
            addCriterion("arrived_time not in", values, "arrivedTime");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeBetween(Date value1, Date value2) {
            addCriterion("arrived_time between", value1, value2, "arrivedTime");
            return (Criteria) this;
        }

        public Criteria andArrivedTimeNotBetween(Date value1, Date value2) {
            addCriterion("arrived_time not between", value1, value2, "arrivedTime");
            return (Criteria) this;
        }

        public Criteria andShipperInfoIsNull() {
            addCriterion("shipper_info is null");
            return (Criteria) this;
        }

        public Criteria andShipperInfoIsNotNull() {
            addCriterion("shipper_info is not null");
            return (Criteria) this;
        }

        public Criteria andShipperInfoEqualTo(String value) {
            addCriterion("shipper_info =", value, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoNotEqualTo(String value) {
            addCriterion("shipper_info <>", value, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoGreaterThan(String value) {
            addCriterion("shipper_info >", value, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoGreaterThanOrEqualTo(String value) {
            addCriterion("shipper_info >=", value, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoLessThan(String value) {
            addCriterion("shipper_info <", value, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoLessThanOrEqualTo(String value) {
            addCriterion("shipper_info <=", value, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoLike(String value) {
            addCriterion("shipper_info like", value, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoNotLike(String value) {
            addCriterion("shipper_info not like", value, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoIn(List<String> values) {
            addCriterion("shipper_info in", values, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoNotIn(List<String> values) {
            addCriterion("shipper_info not in", values, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoBetween(String value1, String value2) {
            addCriterion("shipper_info between", value1, value2, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andShipperInfoNotBetween(String value1, String value2) {
            addCriterion("shipper_info not between", value1, value2, "shipperInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoIsNull() {
            addCriterion("receiver_info is null");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoIsNotNull() {
            addCriterion("receiver_info is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoEqualTo(String value) {
            addCriterion("receiver_info =", value, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoNotEqualTo(String value) {
            addCriterion("receiver_info <>", value, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoGreaterThan(String value) {
            addCriterion("receiver_info >", value, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_info >=", value, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoLessThan(String value) {
            addCriterion("receiver_info <", value, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoLessThanOrEqualTo(String value) {
            addCriterion("receiver_info <=", value, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoLike(String value) {
            addCriterion("receiver_info like", value, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoNotLike(String value) {
            addCriterion("receiver_info not like", value, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoIn(List<String> values) {
            addCriterion("receiver_info in", values, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoNotIn(List<String> values) {
            addCriterion("receiver_info not in", values, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoBetween(String value1, String value2) {
            addCriterion("receiver_info between", value1, value2, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andReceiverInfoNotBetween(String value1, String value2) {
            addCriterion("receiver_info not between", value1, value2, "receiverInfo");
            return (Criteria) this;
        }

        public Criteria andWaybillIsNull() {
            addCriterion("waybill is null");
            return (Criteria) this;
        }

        public Criteria andWaybillIsNotNull() {
            addCriterion("waybill is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillEqualTo(String value) {
            addCriterion("waybill =", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillNotEqualTo(String value) {
            addCriterion("waybill <>", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillGreaterThan(String value) {
            addCriterion("waybill >", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillGreaterThanOrEqualTo(String value) {
            addCriterion("waybill >=", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillLessThan(String value) {
            addCriterion("waybill <", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillLessThanOrEqualTo(String value) {
            addCriterion("waybill <=", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillLike(String value) {
            addCriterion("waybill like", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillNotLike(String value) {
            addCriterion("waybill not like", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillIn(List<String> values) {
            addCriterion("waybill in", values, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillNotIn(List<String> values) {
            addCriterion("waybill not in", values, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillBetween(String value1, String value2) {
            addCriterion("waybill between", value1, value2, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillNotBetween(String value1, String value2) {
            addCriterion("waybill not between", value1, value2, "waybill");
            return (Criteria) this;
        }

        public Criteria andTrackInfoIsNull() {
            addCriterion("track_info is null");
            return (Criteria) this;
        }

        public Criteria andTrackInfoIsNotNull() {
            addCriterion("track_info is not null");
            return (Criteria) this;
        }

        public Criteria andTrackInfoEqualTo(String value) {
            addCriterion("track_info =", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoNotEqualTo(String value) {
            addCriterion("track_info <>", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoGreaterThan(String value) {
            addCriterion("track_info >", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoGreaterThanOrEqualTo(String value) {
            addCriterion("track_info >=", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoLessThan(String value) {
            addCriterion("track_info <", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoLessThanOrEqualTo(String value) {
            addCriterion("track_info <=", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoLike(String value) {
            addCriterion("track_info like", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoNotLike(String value) {
            addCriterion("track_info not like", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoIn(List<String> values) {
            addCriterion("track_info in", values, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoNotIn(List<String> values) {
            addCriterion("track_info not in", values, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoBetween(String value1, String value2) {
            addCriterion("track_info between", value1, value2, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoNotBetween(String value1, String value2) {
            addCriterion("track_info not between", value1, value2, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Long value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Long value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Long value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Long value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Long value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Long value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Long> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Long> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Long value1, Long value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Long value1, Long value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNull() {
            addCriterion("volume is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("volume is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(Long value) {
            addCriterion("volume =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(Long value) {
            addCriterion("volume <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(Long value) {
            addCriterion("volume >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(Long value) {
            addCriterion("volume >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(Long value) {
            addCriterion("volume <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(Long value) {
            addCriterion("volume <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<Long> values) {
            addCriterion("volume in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<Long> values) {
            addCriterion("volume not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(Long value1, Long value2) {
            addCriterion("volume between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(Long value1, Long value2) {
            addCriterion("volume not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andSignImageIsNull() {
            addCriterion("sign_image is null");
            return (Criteria) this;
        }

        public Criteria andSignImageIsNotNull() {
            addCriterion("sign_image is not null");
            return (Criteria) this;
        }

        public Criteria andSignImageEqualTo(String value) {
            addCriterion("sign_image =", value, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageNotEqualTo(String value) {
            addCriterion("sign_image <>", value, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageGreaterThan(String value) {
            addCriterion("sign_image >", value, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageGreaterThanOrEqualTo(String value) {
            addCriterion("sign_image >=", value, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageLessThan(String value) {
            addCriterion("sign_image <", value, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageLessThanOrEqualTo(String value) {
            addCriterion("sign_image <=", value, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageLike(String value) {
            addCriterion("sign_image like", value, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageNotLike(String value) {
            addCriterion("sign_image not like", value, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageIn(List<String> values) {
            addCriterion("sign_image in", values, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageNotIn(List<String> values) {
            addCriterion("sign_image not in", values, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageBetween(String value1, String value2) {
            addCriterion("sign_image between", value1, value2, "signImage");
            return (Criteria) this;
        }

        public Criteria andSignImageNotBetween(String value1, String value2) {
            addCriterion("sign_image not between", value1, value2, "signImage");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredIsNull() {
            addCriterion("is_delivered is null");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredIsNotNull() {
            addCriterion("is_delivered is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredEqualTo(Integer value) {
            addCriterion("is_delivered =", value, "isDelivered");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredNotEqualTo(Integer value) {
            addCriterion("is_delivered <>", value, "isDelivered");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredGreaterThan(Integer value) {
            addCriterion("is_delivered >", value, "isDelivered");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delivered >=", value, "isDelivered");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredLessThan(Integer value) {
            addCriterion("is_delivered <", value, "isDelivered");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredLessThanOrEqualTo(Integer value) {
            addCriterion("is_delivered <=", value, "isDelivered");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredIn(List<Integer> values) {
            addCriterion("is_delivered in", values, "isDelivered");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredNotIn(List<Integer> values) {
            addCriterion("is_delivered not in", values, "isDelivered");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredBetween(Integer value1, Integer value2) {
            addCriterion("is_delivered between", value1, value2, "isDelivered");
            return (Criteria) this;
        }

        public Criteria andIsDeliveredNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delivered not between", value1, value2, "isDelivered");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}