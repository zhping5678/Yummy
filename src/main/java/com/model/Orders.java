package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.OrderState;
import com.vo.OrderInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
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

    private String store_name;//店铺名字

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @OneToMany(targetEntity = GoodList.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<GoodList> goodLists;

    private String note;//订单备注

    private double discount;//优惠金额

    private double money;//订单金额
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    private Date date;//下单时间

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    private Date arrive;//送达时间 或 预计送达时间

    private String name;//收货人姓名

    private String tel;//收货人电话

    private String address;//收货地址

    private String account;//支付账户

    public Orders(OrderInfo orderInfo){
        this.customer=orderInfo.getUsername();
        this.store_id=orderInfo.getStore_id();
        this.store_name=orderInfo.getStore_name();
        this.name=orderInfo.getName();
        this.tel=orderInfo.getTel();
        this.address=orderInfo.getAddress();
        this.account=orderInfo.getAccount();
        this.date=new Date();
        Calendar time=Calendar.getInstance();
        time.add(Calendar.MINUTE,orderInfo.getMinute());
        this.arrive=time.getTime();
        this.note=orderInfo.getNote();
        this.money=orderInfo.getMoney();
        this.discount=orderInfo.getDiscount();
    }
}
