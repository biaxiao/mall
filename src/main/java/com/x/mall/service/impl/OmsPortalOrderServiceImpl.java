package com.x.mall.service.impl;

import com.x.mall.common.api.CommonResult;
import com.x.mall.component.CancelOrderSender;
import com.x.mall.dao.OrderParam;
import com.x.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理service
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        // todo 执行一系列下单操作,具体参考mall
        LOGGER.info("process generateOrder");
        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（OrderId应该在下单后生成）
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null,"下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        // todo 执行一系列取消订单的操作，具体参考mall
        LOGGER.info("process cancelOrder orderId:{}",orderId);
    }


    private void sendDelayMessageCancelOrder(Long orderId){
        //获取订单超时时间，假设为60分钟（测试用的30s）
        long delayTimes= 30*1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId,delayTimes);
    }
}
