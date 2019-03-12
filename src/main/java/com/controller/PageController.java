package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController{

    @RequestMapping(value = "/yummy", method = RequestMethod.GET)
    public String getIndex(){
        return "index";
    }

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
     * 用户找回密码界面
     */
    @RequestMapping(value = "/findBackPass", method = RequestMethod.GET)
    public String getFindBackPassPage(){
        return "user/forgetPass";
    }

    /*
     * 重置密码界面
     */
    @RequestMapping(value = "/modifyPass", method = RequestMethod.GET)
    public String modifyPassword(){
        return "user/modifyPassword";
    }

    /*
     * 用户激活邮箱账号后，完善信息界面
     */
    @RequestMapping(value = "/firstCompleteInfo", method = RequestMethod.GET)
    public String getFirstCompleteInfoPage(){
        return "user/completeInfo";
    }

    /*
     * 显示普通用户登录进入之后的首页
     */
    @RequestMapping(value = "/user_home", method = RequestMethod.GET)
    public String getCustomerHome(){
        return "customer/customerHome";
    }

    /*
     * 显示普通用户查看店铺详细商品界面
     */
    @RequestMapping(value = "/storeDetail", method = RequestMethod.GET)
    public String getStockListPage(){
        return "customer/storeDetail";
    }

    /*
     * 显示普通用户个人中心界面
     */
    @RequestMapping(value = "/personal",method = RequestMethod.GET)
    public String getPersonalPage(){
        return "customer/personalPage";
    }

    /*
     * 显示普通用户订单列表页面
     */
    @RequestMapping(value = "/myOrder",method = RequestMethod.GET)
    public String getMyOrder(){
        return "customer/myOrderPage";
    }

    /*
     * 显示普通用户的消费统计数据
     */
    @RequestMapping(value = "/myStatistics",method = RequestMethod.GET)
    public String getMyStatistics(){
        return "customer/statistics";
    }
    /*
     * 显示商户登录进入后的页面
     */
    @RequestMapping(value = "/store_home", method = RequestMethod.GET)
    public String getStoreHome(){
        return "store/storeHome";
    }

    /*
     * 商户订单管理界面
     */
    @RequestMapping(value = "/orderManage", method = RequestMethod.GET)
    public String getStoreOrderManagePage(){
        return "store/storeOrder";
    }

    /*
     * 商户商品管理界面
     */
    @RequestMapping(value = "/stockManage", method = RequestMethod.GET)
    public String getStoreStockManagePage(){
        return "store/stockManage";
    }

    /*
     * 商户统计数据页面
     */
    @RequestMapping(value = "/financial", method = RequestMethod.GET)
    public String getStoreFinancialPage(){
        return "store/financial";
    }

    /*
     * 系统管理员主页
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminHome(){
        return "admin/adminHome";
    }
}