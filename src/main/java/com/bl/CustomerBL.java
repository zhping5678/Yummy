package com.bl;

import com.blservice.CustomerBLService;
import com.dao.CustomerDao;
import com.model.Customer;
import com.util.MailUtil;
import com.util.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.util.ResultMessage;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerBL implements CustomerBLService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public ResultMessage signUpByEmail(String email, String password) {
        try {
            Customer customer = customerDao.find(email);
            if(customer == null ){//该邮箱之前未注册过
                String activeCode = MailUtil.generateCode();
                Customer newCustomer = new Customer();
//                Customer newCustomer=new Customer(email,password,"","",0,UserState.WaitToActive, activeCode,"",0);
                newCustomer.setEmail(email);
                newCustomer.setPassword(password);
                newCustomer.setState(UserState.WaitToActive);
                newCustomer.setActivecode(activeCode);
                newCustomer.setLevel(0);
                customerDao.save(newCustomer);
                new Thread( new MailUtil(email, activeCode)).start();
//                MailUtil.sendMail(email, activeCode);
                //发送邮件
                return ResultMessage.SUCCESS;
            }else if(customer.getState() == UserState.WaitToActive){//该邮箱注册过，但是未激活
                String activeCode = MailUtil.generateCode();
                customer.setActivecode(activeCode);
                customerDao.saveAndFlush(customer);
                new Thread( new MailUtil(email, activeCode)).start();
//                MailUtil.sendMail(email, activeCode);
                //再次发送邮件
                return ResultMessage.SUCCESS;
            }else{//该邮箱已经被注册过，正在使用中或者已被注销
                return ResultMessage.EXIST;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }

    }

    @Override
    @Transactional
    public ResultMessage activeUser(String code) {
        if(customerDao.activeUser(code)==1){
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAIL;
    }

    @Override
    public ResultMessage firstCompleteInfo(String email, String pass, String name, String tel, String bankCard) {
        try {
            Customer customer = customerDao.find(email);
            if (customer == null) {
                return ResultMessage.FAIL;
            } else if (!customer.getPassword().equals(pass)) {
                return ResultMessage.PassError;
            } else {
                customer.setName(name);
                customer.setTelephone(tel);
                customer.setBankAccount(bankCard);
                customer.setBalance(getRandomBalance());

                customerDao.save(customer);
                return ResultMessage.SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    @Override
    public ResultMessage customerLogin(String username, String password) {
        try {
            Customer customer = customerDao.find(username);
            if (customer == null) {
                return ResultMessage.NotExist;
            } else if (customer.getState() == UserState.CloseAccount) {
                return ResultMessage.InValid;
            } else if(customer.getState() == UserState.WaitToActive){
                return ResultMessage.ToActive;
            } else if (!password.equals(customer.getPassword())) {
                return ResultMessage.PassError;
            } else {
                return ResultMessage.CustomerLogin;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    private int getRandomBalance(){
        return (int) (Math.random()*1000);
    }
}
