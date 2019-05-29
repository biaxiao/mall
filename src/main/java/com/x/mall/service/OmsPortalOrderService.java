package com.x.mall.service;

import com.x.mall.common.api.CommonResult;
import com.x.mall.dao.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理service
 */
public interface OmsPortalOrderService {
    /**
     *  根据提交的信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
