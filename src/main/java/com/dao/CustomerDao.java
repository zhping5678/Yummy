package com.dao;

import com.model.Customer;
import com.util.ResultMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, String> {

     @Query(value = "select c from Customer c where c.email=:email")
     Customer find(@Param(value = "email") String email);

     @Modifying
     @Query(value = "update Customer c set c.state='Valid' where c.activecode=:code and c.state='WaitToActive'")
     int activeUser(@Param("code")String code);
}
