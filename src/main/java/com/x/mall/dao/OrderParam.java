package com.x.mall.dao;

import lombok.Getter;
import lombok.Setter;

/**
 * 生成订单时传入的参数
 */
@Getter
@Setter
public class OrderParam {
    //收货地址id
    private Long memberReceiveAddressId;
    //优惠卷id
    private Long couponId;
    //使用积分数
    private Integer useIntegration;
    //支付方式
    private Integer payType;

}
