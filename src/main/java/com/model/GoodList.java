package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String good_id;//该商品ID

    private int amount;//该商品数量

    private double price;//该商品单价
}
