package com.niuran.giftstore.request;

import com.niuran.giftstore.model.Gift;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Rich on 2021-02-19 22:30
 * @Projcet giftstore
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class GiftRequest extends Gift implements Serializable {
    private Long quantity;
    private Integer page = 1;
    private Integer size = 10;
    private Date startTime;
    private Date endTime;
    private String orderClause;
    private String search;
}
