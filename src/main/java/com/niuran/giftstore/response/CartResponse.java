package com.niuran.giftstore.response;

import com.niuran.giftstore.model.Cart;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Rich on 2021-02-19 21:40
 * @Projcet giftstore
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CartResponse extends Cart implements Serializable {
    private List<CartDetailResponse> cartDetailList;
}
