package com.model;

import com.util.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;

    private String customer;//顾客ID，为邮箱

    private OrderState state;

    private Address address;//收获地址

    private Date date;//下单时间

    private Date payTime;//付款时间

    private Date expectTime;//预计送达时间

    private Date actualTime;//实际送达时间

    private String note;//订单备注

    private List<GoodList> goodLists;

    private double money;//订单金额

    private double discount;//优惠金额
}
