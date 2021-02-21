package com.niuran.giftstore.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MrD
 */
@Data
@ToString
public class UserBaseInfo implements Serializable {
    private static final long serialVersionUID = 4753692760885084450L;
    private Long id;
    private String mobile;
    private String avatar;
    private String realName;
    private Integer sex;
    private Date createtime;
    private Date updatetime;
    private Integer status;
}