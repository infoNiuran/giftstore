package com.niuran.giftstore.response;

import com.niuran.giftstore.model.Gift;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author Rich on 2021-04-01 13:48
 * @Projcet giftstore
 */
@Data
@ToString
public class GiftResponse extends Gift implements Serializable {
    private Long salesVolume;
}
