package com.controller;

import com.blservice.StoreBLService;
import com.blservice.CustomerBLService;
import com.model.Store;
import com.util.StoreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.util.ResultMessage;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Controller
public class UserController {

    @Autowired
    private CustomerBLService customerBLService;
    @Autowired
    private StoreBLService storeBLService;

    /**
     * 普通消费者通过邮箱注册
     * @param email 注册使用邮箱账号
     * @return 注册结果
     */
    @RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
    public @ResponseBody ResultMessage signUpByEmail(String email, String password){
//        System.out.println("Controller email: "+email+", password: "+password);
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
        if(customerBLService.activeUser(code)==ResultMessage.SUCCESS){
            return "user/activeSuccess";
        }else{
            return "user/activeError";
        }
    }

    /*
     * 激活成功后，完善信息
     */
    @RequestMapping(value = "/completeInfo", method = RequestMethod.POST)
    public @ResponseBody ResultMessage completeInfo(String email, String password, String first_name,
                                                    String telephone, String bank_card){
        return customerBLService.firstCompleteInfo(email, password, first_name, telephone, bank_card);
    }

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
        }else{
            return storeBLService.storeLogin(username,password);
        }
    }

    @RequestMapping(value = "/getNextStoreId", method = RequestMethod.POST)
    public @ResponseBody String getNextStoreId(){
        try{
            File file = new File("counter.txt");
            if(file.exists()){
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//            String line = reader.readLine();
                int count = Integer.valueOf(reader.readLine());
//                System.out.println("controller 中读取数值: "+count);
                StringBuilder nextId = new StringBuilder(String.valueOf(count + 1));
                while (nextId.length()<7){
                    nextId.insert(0, "0");
                }
                reader.close();
                return nextId.toString();
            }else{
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(0);
                bufferedWriter.flush();
                bufferedWriter.close();
                return "0000001";
            }
        }catch (IOException e){
            e.printStackTrace();
            return "-1";
        }
    }

    @RequestMapping(value = "/applyStore", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage applyNewStore(String store_id, String store_name, String store_boss, String store_email, String store_pass,
                                StoreType store_type, String province, String city, String area, String detail_address){
        return storeBLService.applyNewStore(store_id, store_name,store_boss, store_email,store_pass,store_type,
                province,city,area,detail_address);
    }

    @RequestMapping(value = "/getStoreInfo", method = RequestMethod.POST)
    public @ResponseBody
    Store getStoreInfo(String store_id){
        return storeBLService.getStoreInfo(store_id);
    }

    @RequestMapping(value = "/modifyIntroduce", method = RequestMethod.POST)
    public @ResponseBody
    void modifyIntroduce(String store_id, String introduce){
        storeBLService.modifyIntroduce(store_id, introduce);
    }

    @RequestMapping(value = "/modifyPass", method = RequestMethod.POST)
    public @ResponseBody ResultMessage modifyPass(String store_id, String oldPass, String newPass){
        return storeBLService.modifyPassword(store_id, oldPass, newPass);
    }
}
