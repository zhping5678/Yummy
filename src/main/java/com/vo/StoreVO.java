package com.vo;

import com.model.Good;
import com.util.StoreType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class StoreVO {

    private String id;//商户7位识别码

    private String name;//店铺名字

    private String boss;//法人代表

    private String email;//联系方式

    private String introduce;//店铺简介

    private String type;//店铺类型

    private List<String> food_types;//食物种类

    //地址
    private String province;//省
    private String city;//市
    private String area;//区
    private String detail;//具体地址

    private String discounts;//满减优惠

    private Map<String,List<Good>> goods;//商品

    public StoreVO(String id,String name,String boss,String email, String introduce,String type,List<String> food_types,
                   String province,String city,String area,String detail){
        this.id=id;
        this.name=name;
        this.boss=boss;
        this.email=email;
        this.introduce=introduce;
        this.type=type;
        this.food_types=food_types;
        this.province=province;
        this.city=city;
        this.area=area;
        this.detail=detail;
    }
}
