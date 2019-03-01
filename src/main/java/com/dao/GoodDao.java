package com.dao;

import com.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodDao extends JpaRepository<Good, Long> {

    @Query(value = "select g from Good g where g.id=:id")
    Good find(@Param("id")long good_id);
}
