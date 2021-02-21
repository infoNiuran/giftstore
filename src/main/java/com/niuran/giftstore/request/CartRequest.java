package com.niuran.giftstore.request;

import com.niuran.giftstore.model.Cart;
import com.niuran.giftstore.model.CartDetail;
import com.niuran.giftstore.model.Gift;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Rich on 2021-02-19 22:18
 * @Projcet giftstore
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CartRequest extends Cart implements Serializable {
    private List<CartDetail> cartDetailList;
    private List<GiftRequest> giftList;
}
