package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController{

    /*
     * 显示登录界面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "user/login";
    }

    /*
     * 显示用户注册界面
     */
    @RequestMapping(value = "/userRegister", method = RequestMethod.GET)
    public String getUserRegisterPage(){
        return "user/userRegister";
    }

    /*
     * 显示商家注册界面
     */
    @RequestMapping(value = "/storeRegister", method = RequestMethod.GET)
    public String getStoreRegisterPage(){
        return "user/storeRegister";
    }

    /*
     * 显示普通用户登录进入之后的首页
     */
    @RequestMapping(value = "/user_home", method = RequestMethod.GET)
    public String getUserHome(){
        return "home";
    }
}
