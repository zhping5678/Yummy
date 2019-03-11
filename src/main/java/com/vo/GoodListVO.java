package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodListVO {

    private long good_id;

    private String good_name;

    private double good_price;

    private int num;
}
