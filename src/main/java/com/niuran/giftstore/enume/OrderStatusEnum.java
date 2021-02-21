package com.niuran.giftstore.enume;

public enum OrderStatusEnum {
    待结算(0), 待发货(1), 待签收(2), 已签收(3), 已取消(4), 已过期(5), 已删除(6);
    private Integer value;

    OrderStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static OrderStatusEnum getEnum(int index) {
        OrderStatusEnum[] c = OrderStatusEnum.class.getEnumConstants();
        return c[index];
    }
}
