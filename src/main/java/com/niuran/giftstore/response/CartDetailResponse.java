package com.niuran.giftstore.response;

import com.niuran.giftstore.model.CartDetail;
import com.niuran.giftstore.model.Gift;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author Rich on 2021-02-19 21:50
 * @Projcet giftstore
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CartDetailResponse extends CartDetail implements Serializable {
    private Gift gift;
}
