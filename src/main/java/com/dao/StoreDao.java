package com.dao;

import com.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreDao extends JpaRepository<Store, String> {

    @Query(value = "select s from Store s where s.id=:id")
    Store find(@Param("id") String id);

    @Modifying
    @Query(value = "update Store s set s.introduce=:introduce where s.id=:id")
    void updateIntroduce(@Param("id") String id,@Param("introduce") String introduce);

    @Query(value = "select s from Store s where s.city=:city and s.state='Valid'")
    List<Store> findValidStoreInSameCity(@Param("city")String cityName);

    @Query(value = "select s.food_types from Store s where s.id=:id")
    String getStoreFoodType(@Param("id")String store_id);

    @Modifying
    @Query(value = "update Store s set s.food_types=:newType where s.id=:id")
    void updateStoreFoodType(@Param("id")String store_id,@Param("newType")String newGoodType);
}
