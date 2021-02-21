package com.niuran.giftstore.request;

import com.niuran.giftstore.model.OrderLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Rich on 2021-02-19 20:39
 * @Projcet giftstore
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderLogRequest extends OrderLog implements Serializable {
    private Integer page = 1;
    private Integer size = 10;
    private Date startTime;
    private Date endTime;
    private String orderClause;
}
