package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatistics {

    private int overOrdersNum;

    private double overOrdersConsume;

    private int cancelOrdersNum;

    private int cancelPayNum;

    private double payForCancelOrders;

    //不同消费金额的订单数量 <20, 20~30, 30~40, >40
    private int[] orderNumsPerPrice;

    //不同类型店铺的消费 食物/超市/……
    private int[] ordersNumPerStoreType;
}
