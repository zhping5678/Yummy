package com.dao;

import com.model.Customer;
import com.util.ResultMessage;

public interface UserDao extends BaseDao{

     Customer findById(String email);

     void save(Customer customer);

     void update(Customer customer);

     ResultMessage activeUser(String active);
}
