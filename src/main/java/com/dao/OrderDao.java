package com.dao;

import com.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Orders,String> {

    @Query(value = "select o from Orders o where o.customer=:username and o.state<>'Cancel' and o.state<>'Over' order by o.date desc")
    List<Orders> getOnOrder(@Param("username")String username);

    @Modifying
    @Query(value = "update Orders o set o.state='WaitStoreToAccept' where o.id=:id")
    int payOrder(@Param("id")String order_id);

    @Modifying
    @Query(value = "update Orders o set o.state='Over' where o.id=:id")
    int confirmOrder(@Param("id")String order_id);

    @Query(value = "select o from Orders o where o.customer=:username and (o.state='Over' or o.state='Cancel')")
    List<Orders> getHistoryOrders(@Param("username") String username);
}
