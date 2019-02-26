package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodList {

    @Id
    private int id;

    private String goodid;//该商品ID

    private int amount;//该商品数量

    private double price;//该商品单价
}
