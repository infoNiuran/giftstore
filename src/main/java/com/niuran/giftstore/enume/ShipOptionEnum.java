package com.niuran.giftstore.enume;

public enum ShipOptionEnum {
    无需物流(0), 需物流(1);
    private Integer value;

    ShipOptionEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static ShipOptionEnum getEnum(int index) {
        ShipOptionEnum[] c = ShipOptionEnum.class.getEnumConstants();
        return c[index];
    }
}
