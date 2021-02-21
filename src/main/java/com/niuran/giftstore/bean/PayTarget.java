package com.niuran.giftstore.bean;

/**
 * @Author MrD on 2018/7/26.
 */
public enum PayTarget {
    查看活信息(0), 充值余额(1), 充值金豆(2), 购买月卡(3), 购买月卡并激活(4), 信息置顶(5),
    充值保证金(6), 缴纳保证金(7), 信息自动刷新(8), 商品发布(9), 商品延时(10),
    购买尾货通卡(11), 购买尾货通卡并激活(12), 商品购买(13), 结算货款入账(14),
    商品展示退款(15),商品展示退金豆(16),
    赔偿金出账(17), 赔偿金入账(18), 商品购买退款(19),
    服务套餐包购买(20),礼品兑换(21);

    private Integer value;
    PayTarget(Integer value) {
        this.value = value;
    }
    public Integer getValue(){
        return value;
    }

    public static PayTarget getEnum(int index){
        PayTarget[] c=PayTarget.class.getEnumConstants();
        return c[index];
    }
}
