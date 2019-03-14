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

     @Modifying
     @Query(value = "update Customer c set c.password=:newPass where c.email=:email and c.state='Valid'")
     int resetPassword(@Param("email")String email,@Param("newPass")String newPass);

     @Modifying
     @Query(value = "update Customer c set c.name=:newName where c.email=:username")
     int resetName(@Param("username")String username,@Param("newName")String newName);


     @Modifying
     @Query(value = "update Customer c set c.telephone=:newTel where c.email=:username")
     int resetTel(@Param("username")String username,@Param("newTel")String newTel);

     @Modifying
     @Query(value = "update Customer c set c.state='CloseAccount' where c.email=:username")
     int closeAccount(@Param("username")String username);

     @Modifying
     @Query(value = "update Customer c set c.level=:level where c.email=:username")
     void updateLevel(@Param("username")String username,@Param("level")int level);
}
