package com.controller;

import com.blservice.UserBLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.util.ResultMessage;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {

    @Autowired
    private UserBLService userBLService;

    /**
     * 普通消费者通过邮箱注册
     * @param email 注册使用邮箱账号
     * @return 注册结果
     */
    @RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
    public @ResponseBody ResultMessage signUpByEmail(String email, String password){
        System.out.println("Controller email: "+email+", password: "+password);
         ResultMessage resultMessage=userBLService.signUpByEmail(email, password);
         System.out.println(resultMessage);
         return resultMessage;
    }

    @RequestMapping(value = "/active")
    public String activeUser(String code){
        System.out.println("code :" +code);
        if(userBLService.activeUser(code)==ResultMessage.SUCCESS){
            return "user/activeSuccess";
        }else{
            return "user/activeError";
        }
    }

    @RequestMapping(value = "/completeInfo", method = RequestMethod.POST)
    public @ResponseBody ResultMessage completeInfo(String email, String password, String first_name,
                                                    String telephone, String bank_card){
        return userBLService.firstCompleteInfo(email, password, first_name, telephone, bank_card);
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public @ResponseBody ResultMessage signIn(String username, String password){
        System.out.println("登录用户名："+username+", 密码："+password);
        if(username.contains("@")){
            return userBLService.customerLogin(username,password);
        }else{
            return userBLService.storeLogin(username,password);
        }
    }
}
