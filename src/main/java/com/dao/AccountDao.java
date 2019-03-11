package com.dao;

import com.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account,String> {

    @Query(value = "select a.balance from Account a where a.account=:id")
    double getBalanceById(@Param("id")String account_id);

    @Modifying
    @Query(value = "update Account a set a.balance=a.balance+:money where a.account=:id")
    int add(@Param("id") String account,@Param("money") double money);

    @Modifying
    @Query(value = "update Account a set a.balance=a.balance-:money where a.account=:id")
    int minus(@Param("id") String account,@Param("money") double money);
}
