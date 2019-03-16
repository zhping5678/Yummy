package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreStatistics {

    private int completeOrderNums;
    private int refuseOrderNums;
    private int cancelBeforeAcceptNums;
    private int cancelAfterAcceptNums;

    private double completeIncome;//抽成前收益
    private double compensate;//抽成前赔付

    private int customerNums;//在本餐厅消费过的会员数

    //8:00-11 11:00-15:00 15:00-19：00 19：00-22:00
    private int[] orderTime;//点餐时间段

    //<20  20-30  30-40 40-50 >50
    private int[] orderCost;//订单的消费金额分布

}