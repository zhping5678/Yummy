package com.blservice;

import com.model.Record;
import com.util.ResultMessage;
import com.util.StoreType;

import java.util.List;

public interface AdminBLService {
    ResultMessage adminLogin(String password);

    ResultMessage newApply(String store_id, String store_name,String boss, String email,String address, StoreType type);

    ResultMessage passApply(long id);

    ResultMessage refuseApply(long id);

    List<Record> getWaitCheckRecords();

    List<Record> getDoneRecords();
}
