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

    @Query(value = "select o from Orders o where o.customer=:username and o.state<>'CancelBeforeAccept' " +
            "and o.state<>'Over' and o.state<>'CancelAfterAccept' and o.state<>'Refuse' order by o.date desc")
    List<Orders> getOnOrder(@Param("username")String username);

    @Modifying
    @Query(value = "update Orders o set o.state='WaitStoreToAccept' where o.id=:id")
    int payOrder(@Param("id")String order_id);

    @Modifying
    @Query(value = "update Orders o set o.state='Over' where o.id=:id")
    int confirmOrder(@Param("id")String order_id);

    @Query(value = "select o from Orders o where o.customer=:username and (o.state='Over' or o.state='CancelAfterAccept'" +
            "or o.state='CancelBeforeAccept' or o.state='Refuse') order by o.date desc")
    List<Orders> getHistoryOrders(@Param("username") String username);


    //店铺查看订单
    @Query(value = "select o from Orders o where o.store_id=:store_id and o.state='WaitStoreToAccept' order by o.date desc")
    List<Orders> getStoreNewOrders(@Param("store_id") String store_id);

    @Query(value = "select o from Orders o where o.store_id=:store_id and o.state='WaitToSendOut' order by o.date desc ")
    List<Orders> getStorePreparingOrders(@Param("store_id")String store_id);

    @Query(value = "select o from Orders o where o.store_id=:store_id and o.state='Sending' order by o.date desc ")
    List<Orders> getStoreSendOrders(@Param("store_id")String store_id);

    @Query(value = "select o from Orders o where o.store_id=:store_id and o.state='Over' order by o.date desc ")
    List<Orders> getStoreCompleteOrders(@Param("store_id")String store_id);

    @Query(value = "select o from Orders o where o.store_id=:store_id and (o.state='CancelAfterAccept' or o.state='Refuse') order by o.date desc ")
    List<Orders> getStoreCancelOrders(@Param("store_id")String store_id);

    //店铺方操作订单
    @Modifying
    @Query(value = "update Orders o set o.state='WaitToSendOut' where o.id=:id and o.state='WaitStoreToAccept'")
    int acceptOrder(@Param("id") String order_id);

    @Modifying
    @Query(value = "update Orders o set o.state='Sending' where o.id=:id and o.state='WaitToSendOut'")
    int sendOutOrder(@Param("id")String order_id);

    @Modifying
    @Query(value = "update Orders o set o.state='Refuse' where o.id=:id and o.state='WaitStoreToAccept'")
    int refuseOrder(@Param("id")String order_id);
}
