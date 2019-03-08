package com.bl;

import com.blservice.OrderBLService;
import com.dao.StoreDao;
import com.model.Orders;
import com.vo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderBL implements OrderBLService {
    @Autowired
    private StoreDao storeDao;


    @Override
    public String submitNewOrder(OrderInfo orderInfo) {
        Orders newOrder=new Orders();
        return null;
    }
}
