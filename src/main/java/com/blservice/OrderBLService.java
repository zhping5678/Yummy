package com.blservice;

import com.model.Orders;
import com.util.ResultMessage;
import com.vo.OrderInfo;

import java.util.List;

public interface OrderBLService {

    String submitNewOrder(OrderInfo orderInfo);

    List<Orders> getOnOrder(String username);

    ResultMessage payOrder(String order_id);

    ResultMessage cancelOrder(String order_id);

    ResultMessage confirmOrder(String order_id);

    List<Orders> getHistoryOrders(String username);
}
