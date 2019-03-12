package com.controller;

import com.blservice.AdminBLService;
import com.blservice.StoreBLService;
import com.blservice.CustomerBLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.util.ResultMessage;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @Autowired
    private CustomerBLService customerBLService;
    @Autowired
    private StoreBLService storeBLService;
    @Autowired
    private AdminBLService adminBLService;

    /**
     * 普通用户或店铺登录
     * @param username 邮箱或店铺编号
     * @param password 登录密码
     * @return 登录结果
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public @ResponseBody ResultMessage signIn(String username, String password){
//        System.out.println("登录用户名："+username+", 密码："+password);
        if(username.contains("@")){
            return customerBLService.customerLogin(username,password);
        }else if (username.equals("admin")){
            return adminBLService.adminLogin(password);
        }else{
            return storeBLService.storeLogin(username,password);
        }
    }

    @RequestMapping(value = "/forgetPass", method = RequestMethod.POST)
    public @ResponseBody ResultMessage forgetPass(String username){
        if(username.contains("@")){//普通用户找回密码
            return customerBLService.findBackPass(username);
        }else{//店铺用户找回密码
            return storeBLService.findBackPass(username);
        }
    }



    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage modifyPass(String username, String oldPass, String newPass){
        if(username.contains("@")){
            return customerBLService.modifyPassword(username, oldPass, newPass);
        }else {
            return storeBLService.modifyPassword(username, oldPass, newPass);
        }
    }


}
