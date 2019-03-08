package com.controller;

import com.blservice.OrderBLService;
import com.vo.OrderInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired
    private OrderBLService orderBLService;

    @RequestMapping(value = "/submitNewOrder", method = RequestMethod.POST)
    public @ResponseBody
    String submitNewOrder(@RequestBody OrderInfo orderInfo){
        System.out.println("提交新订单的内容为："+orderInfo.toString());
        return orderBLService.submitNewOrder(orderInfo);
    }
}
