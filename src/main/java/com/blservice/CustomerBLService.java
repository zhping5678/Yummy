package com.blservice;

import com.util.ResultMessage;

public interface CustomerBLService {

    ResultMessage signUpByEmail(String email, String password);

    ResultMessage activeUser(String code);

    ResultMessage firstCompleteInfo(String email,String pass, String name,String tel, String bankCard);

    ResultMessage customerLogin(String username, String password);
}
