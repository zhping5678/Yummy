package com.view;

import com.model.Orders;
import com.model.Store;
import com.util.OrderState;
import com.util.StoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerView {

    private double money;

    private OrderState state;

    private StoreType storeType;

    private Date time;

    public CustomerView(Orders o, Store s){
        this.money=o.getMoney();
        this.state=o.getState();
        this.storeType=s.getType();
        this.time=o.getDate();
    }
}
