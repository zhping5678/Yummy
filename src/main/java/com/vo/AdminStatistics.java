package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStatistics {

    private int validCustomer;//正在使用的用户
    private int inValidCustomer;//已注销的用户
    //0--5级
    private int[] levels;//不同等级的用户比例

    private int validStore;//正在售卖的店铺数
    private int inValidStore;//已下架的店铺数
    private int[] types;//各个类型的店铺比例

    private double totalMoney;//总收益
    //今年每月收益变化曲线2019年开始
    private double[] moneyPerMonth;
}
