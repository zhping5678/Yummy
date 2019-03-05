package com.controller;

import com.blservice.CustomerBLService;
import com.model.Account;
import com.model.Address;
import com.util.ResultMessage;
import com.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerBLService customerBLService;

    /**
     * 普通消费者通过邮箱注册
     * @param email 注册使用邮箱账号
     * @return 注册结果
     */
    @RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
    public @ResponseBody ResultMessage signUpByEmail(String email, String password){
        return customerBLService.signUpByEmail(email, password);
    }

    /**
     * 普通用户点击邮箱链接进行验证
     * @param code 验证token
     * @return 是否激活成功
     */
    @RequestMapping(value = "/active")
    public String activeUser(String code){
        System.out.println("code :" +code);
        if(customerBLService.activeUser(code)== ResultMessage.SUCCESS){
            return "user/activeSuccess";
        }else{
            return "user/activeError";
        }
    }

    /*
     * 激活成功后，完善信息
     */
    @RequestMapping(value = "/completeInfo", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage completeInfo(String email, String password, String first_name,
                               String telephone, String bank_card){
        return customerBLService.firstCompleteInfo(email, password, first_name, telephone, bank_card);
    }

    /*
     * 注销账户
     */
    @RequestMapping(value = "/closeAccount", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage closeAccount(String username){
        return customerBLService.closeAccount(username);
    }

    /*
     * 修改用户名
     */
    @RequestMapping(value = "/modifyName",method = RequestMethod.POST)
    public @ResponseBody
    void modifyName(String username,String newName){
        customerBLService.modifyName(username,newName);
    }
    /*
     * 修改联系电话
     */
    @RequestMapping(value = "/modifyTel",method = RequestMethod.POST)
    public @ResponseBody
    void modifyTel(String username,String newTel){
        customerBLService.modifyTel(username,newTel);
    }

    @RequestMapping(value = "/getCustomerInfo", method = RequestMethod.POST)
    public @ResponseBody
    CustomerVO getCustomerInfo(String username){
        return customerBLService.getCustomerInfo(username);
    }

    @RequestMapping(value = "/getLevel",method = RequestMethod.POST)
    public @ResponseBody
    int getUserLevel(String username){
        return customerBLService.getCustomerLevel(username);
    }

    @RequestMapping(value = "/getAddressList", method = RequestMethod.POST)
    public @ResponseBody
    List<Address> getAddressList(String username){
        return customerBLService.getAddressList(username);
    }

    @RequestMapping(value = "/addNewAddress", method = RequestMethod.POST)
    public @ResponseBody
    void addNewAddress(String username,String province,String city,String area,String detail,String tel,String name){
        customerBLService.addNewAddress(username,province,city,area,detail,tel,name);
    }

    @RequestMapping(value = "/deleteAddress",method = RequestMethod.POST)
    public @ResponseBody
    void deleteAddress(String username, long address_id){
        customerBLService.deleteAddress(username,address_id);
    }

    @RequestMapping(value = "/getAccountList", method = RequestMethod.POST)
    public @ResponseBody
    List<Account> getAccountList(String username){
        return customerBLService.getAccountList(username);
    }

    @RequestMapping(value = "/addNewAccount", method = RequestMethod.POST)
    public @ResponseBody
    void addNewAccount(String username,String account){
        customerBLService.addNewAccount(username,account);
    }

    @RequestMapping(value = "/deleteAddress",method = RequestMethod.POST)
    public @ResponseBody
    void deleteAccount(String username, String account){
        customerBLService.deleteAccount(username,account);
    }
}
