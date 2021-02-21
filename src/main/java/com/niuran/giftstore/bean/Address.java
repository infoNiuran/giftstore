package com.niuran.giftstore.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Address {
    private Integer id;

    private Long userId;

    private String addr;

    private String realName;

    private String addressDetail;

    private String mobile;

}