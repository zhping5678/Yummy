package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    @Id
    private long id;

    private String email;//地址持有人

    private String province;//省

    private String city;//市

    private String area;//区

    private String detail;//详细地址

    private String telephone;//收货电话

    private String name;//收货人
}
