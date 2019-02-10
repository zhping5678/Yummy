package com.dao;

import com.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreDao extends JpaRepository<Store, String> {

    @Query(value = "select s from Store s where s.id=:id")
    Store find(@Param("id") String id);
}
