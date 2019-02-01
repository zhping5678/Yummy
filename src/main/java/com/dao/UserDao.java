package com.dao;

import com.model.Customer;
import com.util.ResultMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Customer, String> {

     @Query(value = "select c from Customer c where c.email=:email")
     Customer find(@Param(value = "email") String email);
}
