package com.model;

import com.util.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    private String id;

    private String customer;//顾客ID，为邮箱

    private String store_id;//店铺ID

    private OrderState state;

    private String tel;//收货人电话

    private String address;//收货地址

    private Date date;//下单时间

    private Date payTime;//付款时间

    private Date expectTime;//预计送达时间

    private Date actualTime;//实际送达时间

    private String note;//订单备注

    @OneToMany(targetEntity = GoodList.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<GoodList> goodLists;

    private double money;//订单金额

    private double discount;//优惠金额
}
