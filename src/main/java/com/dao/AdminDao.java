package com.dao;

import com.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao extends JpaRepository<Record,Long>{

    @Query(value = "select r from Record r where r.state='WaitToCheck' order by r.applyTime desc ")
    List<Record> getWaitCheckRecords();

    @Query(value = "select r from Record r where r.state<>'WaitToCheck' order by r.applyTime desc ")
    List<Record> getDoneRecords();
}
