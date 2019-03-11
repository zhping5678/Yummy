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

    List<Orders> getStoreNewOrders(String store_id);

    List<Orders> getStorePreparingOrders(String store_id);

    List<Orders> getStoreSendOrders(String store_id);

    List<Orders> getStoreCompleteOrders(String store_id);

    List<Orders> getStoreCancelOrders(String store_id);

    ResultMessage acceptOrder(String order_id);

    ResultMessage sendOutOrder(String order_id);

    ResultMessage refuseOrder(String order_id);

}
