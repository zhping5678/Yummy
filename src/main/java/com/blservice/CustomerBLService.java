package com.blservice;

import com.model.Account;
import com.model.Address;
import com.util.ResultMessage;
import com.vo.CustomerVO;

import java.util.List;

public interface CustomerBLService {

    ResultMessage signUpByEmail(String email, String password);

    ResultMessage activeUser(String code);

    ResultMessage closeAccount(String username);

    ResultMessage firstCompleteInfo(String email,String pass, String name,String tel, String bankCard);

    ResultMessage customerLogin(String username, String password);

    ResultMessage findBackPass(String email);

    ResultMessage modifyPassword(String username, String oldPass, String newPass);

    void modifyName(String username,String newName);

    void modifyTel(String username,String newTel);

    int getCustomerLevel(String username);

    CustomerVO getCustomerInfo(String username);

    List<Address> getAddressList(String username);

    ResultMessage addNewAddress(String username,String province,String city,String area,String detail,String tel,String name);

    ResultMessage deleteAddress(String username,List<String> address_id);

    List<Account> getAccountList(String username);

    ResultMessage addNewAccount(String username,String account);

    ResultMessage deleteAccount(String username,List<String> account);

    double getBalance(String account_id);

    int accountIn(String account,double money);

    int accountOut(String account,double money);
}
