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

    void addNewAddress(String username,String province,String city,String area,String detail,String tel,String name);

    void deleteAddress(String username,long address_id);

    List<Account> getAccountList(String username);

    void addNewAccount(String username,String account);

    void deleteAccount(String username,String account);
}
