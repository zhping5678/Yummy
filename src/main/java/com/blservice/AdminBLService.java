package com.blservice;

import com.model.Record;
import com.util.ResultMessage;

import java.util.List;

public interface AdminBLService {
    ResultMessage adminLogin(String password);

    ResultMessage newApply(String store_id);

    ResultMessage passApply(long id,String store_id);

    ResultMessage refuseApply(long id,String store_id);

    List<Record> getWaitCheckRecords();

    List<Record> getDoneRecords();
}
