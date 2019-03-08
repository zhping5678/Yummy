package com.dao;

import com.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GoodDao extends JpaRepository<Good, Long> {

    @Query(value = "select g from Good g where g.id=:id")
    Good find(@Param("id")long good_id);

    @Modifying
    @Query(value = "update Good g set g.state='Valid' where g.state='Wait' and g.start_time< ?1")
    int updateWaitGood(Date date);

    @Modifying
    @Query(value = "update Good g set g.state='InValid' where g.state='Valid' and g.end_time< ?1")
    int updateValidGood(Date date);

}
