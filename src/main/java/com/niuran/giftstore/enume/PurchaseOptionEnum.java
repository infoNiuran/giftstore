package com.niuran.giftstore.enume;

public enum PurchaseOptionEnum {
    现金(0), 金豆(1), 混合(2);
    private Integer value;

    PurchaseOptionEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static PurchaseOptionEnum getEnum(int index) {
        PurchaseOptionEnum[] c = PurchaseOptionEnum.class.getEnumConstants();
        return c[index];
    }
}
