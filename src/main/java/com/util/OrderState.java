package com.util;

public enum OrderState {
    WaitToPay,//等待付款
    WaitStoreToAccept,//已付款，等待商家接单
    WaitToTakeGoods,//商家已接单，等待客户确认收货
    OverTime,//订单超时（配送中）
    WaitToRefund,//等待退款
}
