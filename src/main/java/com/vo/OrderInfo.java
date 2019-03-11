package com.vo;

import com.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    private String store_id;

    private String store_name;

    private String username;

    private List<GoodListVO> list;

    private String name;//收货人姓名

    private String tel;//收货人电话

    private String address;//收货地址

    private String account;

    private int minute;//预计送达时间

    private String note;//订单备注

    private double money;//订单最终总金额

    private double discount;//优惠金额
}
