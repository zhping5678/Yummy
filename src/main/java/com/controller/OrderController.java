package com.controller;

import com.blservice.OrderBLService;
import com.model.Orders;
import com.util.ResultMessage;
import com.vo.OrderInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderBLService orderBLService;

    /*
     * 用户提交新订单
     */
    @RequestMapping(value = "/submitNewOrder", method = RequestMethod.POST)
    public @ResponseBody String submitNewOrder(@RequestBody OrderInfo orderInfo){
//        System.out.println("提交新订单的内容为："+orderInfo.toString());
        return orderBLService.submitNewOrder(orderInfo);
    }

    /*
     * 得到正在进行中的订单
     */
    @RequestMapping(value = "/getOnOrder",method = RequestMethod.POST)
    public @ResponseBody
    List<Orders> getOnOrder(String username){
        return orderBLService.getOnOrder(username);
    }

    /*
     * 确认付款
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public @ResponseBody ResultMessage pay(String order_id){
        System.out.println("pay:"+order_id);
        return orderBLService.payOrder(order_id);
    }

    /*
     * 取消订单
     */
    @RequestMapping(value = "/cancelOrder",method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage cancelOrder(String order_id){
        return orderBLService.cancelOrder(order_id);
    }

    /*
     * 确认收货
     */
    @RequestMapping(value = "/confirmOrder",method = RequestMethod.POST)
    public @ResponseBody ResultMessage confirmOrder(String order_id){
        return orderBLService.confirmOrder(order_id);
    }

    /*
     * 得到历史订单，即结束的或取消的
     */
    @RequestMapping(value = "/getHistoryOrders", method = RequestMethod.POST)
    public @ResponseBody List<Orders> getHistoryOrders(String username){
        return orderBLService.getHistoryOrders(username);
    }


    /*
     * 商家得到新订单
     */
    @RequestMapping(value = "/getNewOrders",method = RequestMethod.POST)
    public @ResponseBody List<Orders> getNewOrders(String store_id){
        return orderBLService.getStoreNewOrders(store_id);
    }
    /*
     * 商家得到备货中的订单
     */
    @RequestMapping(value = "/getPrepareOrders",method = RequestMethod.POST)
    public @ResponseBody List<Orders> getPreparingOrders(String store_id){
        return orderBLService.getStorePreparingOrders(store_id);
    }
    /*
     * 商家得到已送出的订单
     */
    @RequestMapping(value = "/getSendOrders",method = RequestMethod.POST)
    public @ResponseBody List<Orders> getSendOrders(String store_id){
        return orderBLService.getStoreSendOrders(store_id);
    }
    /*
     * 商家得到以完成的订单
     */
    @RequestMapping(value = "/getCompleteOrders",method = RequestMethod.POST)
    public @ResponseBody List<Orders> getCompleteOrders(String store_id){
        return orderBLService.getStoreCompleteOrders(store_id);
    }

    /*
     * 商家得到被取消的订单
     */
    @RequestMapping(value = "/getCancelOrders",method = RequestMethod.POST)
    public @ResponseBody List<Orders> getCancelOrders(String store_id){
        return orderBLService.getStoreCancelOrders(store_id);
    }

    /*
     * 商家接单
     */
    @RequestMapping(value = "/acceptOrder",method = RequestMethod.POST)
    public @ResponseBody ResultMessage accept(String order_id){
        return orderBLService.acceptOrder(order_id);
    }

    /*
     * 商家送出商品
     */
    @RequestMapping(value = "/sendOutOrder",method = RequestMethod.POST)
    public @ResponseBody ResultMessage sendOut(String order_id){
        return orderBLService.sendOutOrder(order_id);
    }

    /*
     * 商家拒单
     */
    @RequestMapping(value = "/refuseOrder",method = RequestMethod.POST)
    public @ResponseBody ResultMessage refuse(String order_id){
        return orderBLService.refuseOrder(order_id);
    }
}
