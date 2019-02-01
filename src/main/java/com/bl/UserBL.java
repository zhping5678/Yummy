package com.bl;

import com.blservice.UserBLService;
import com.dao.UserDao;
import com.model.Customer;
import com.util.MailUtil;
import com.util.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.util.ResultMessage;

@Service
public class UserBL implements UserBLService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResultMessage signUpByEmail(String email, String password) {
        try {
            Customer customer = userDao.findById(email);
            if(customer == null ){//该邮箱之前未注册过
                String activeCode = MailUtil.generateCode();
                Customer newCustomer=new Customer(email,password,0,UserState.WaitToActive, activeCode);
                userDao.save(newCustomer);
                MailUtil.sendMail(email, activeCode);
                //发送邮件
                return ResultMessage.SUCCESS;
            }else if(customer.getState() == UserState.WaitToActive){//该邮箱注册过，但是未激活
                String activeCode = MailUtil.generateCode();
                customer.setActivecode(activeCode);
                userDao.update(customer);
                MailUtil.sendMail(email, activeCode);
                //再次发送邮件
                return ResultMessage.SUCCESS;
            }else{//该邮箱已经被注册过，正在使用中或者已被注销
                return ResultMessage.EXIST;
            }
        }catch (Exception e){
            return ResultMessage.FAIL;
        }

    }

    @Override
    public ResultMessage activeUser(String code) {
        return userDao.activeUser(code);
    }
}
