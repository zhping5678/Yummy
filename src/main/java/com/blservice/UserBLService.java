package com.blservice;

import com.util.ResultMessage;

public interface UserBLService {

    ResultMessage signUpByEmail(String email, String password);

    ResultMessage activeUser(String code);
}
