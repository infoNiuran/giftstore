package com.niuran.giftstore.bean;

/**
 * @Author MrD on 2018/7/26.
 */
public enum PayType {
    系统赠送(0) , 微信(1) , 支付宝(2), 余额(3), 金豆(4),
    小程序内支付(5), 苹果内购(6), 平台结算(7),微信公众号支付(8),NATIVE支付(9);
    private Integer value;
    PayType(Integer value) {
        this.value = value;
    }
    public Integer getValue(){
        return value;
    }

    public static PayType getEnum(int index){
        PayType[] c=PayType.class.getEnumConstants();
        return c[index];
    }
}
