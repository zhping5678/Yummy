package com.model;

import com.util.StoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    private String id;//商户7位识别码

    private String name;//店铺名字

    private String boss;//法人代表

    private String password;//登录密码

    private String email;//联系方式

    private String introduce;//店铺简介

    @ElementCollection(targetClass = StoreType.class, fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<StoreType> type;//店铺类型

    //地址
    private String province;//
    private String city;//市
    private String area;//区
    private String detail;//具体地址
}
