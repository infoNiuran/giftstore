package com.niuran.giftstore.enume;

/**
 * @Author Rich on 2020-01-10 17:57
 * @Projcet mall
 */
public enum DeliveryStatusEnum {
    创建(0), 投递(1), 签收(2);
    private Integer value;

    DeliveryStatusEnum(Integer value) {
        this.value = value;
    }
    public Integer getValue(){
        return value;
    }

    public static DeliveryStatusEnum getEnum(int index){
        DeliveryStatusEnum[] c= DeliveryStatusEnum.class.getEnumConstants();
        return c[index];
    }
}
