package com.bl;

import com.blservice.AdminBLService;
import com.blservice.StoreBLService;
import com.dao.AdminDao;
import com.model.Record;
import com.util.CheckState;
import com.util.ResultMessage;
import com.util.StoreType;
import com.util.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AdminBL implements AdminBLService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private StoreBLService storeBLService;

    private static final String pass="admin";
    @Override
    public ResultMessage adminLogin(String password) {
        if(password.equals(pass)){
            return ResultMessage.AdminLogin;
        }else{
            return ResultMessage.PassError;
        }
    }

    @Override
    public ResultMessage newApply(String store_id, String store_name,String boss, String email,String address, StoreType type) {
        Record newRecord=new Record(0,new Date(),null,store_id,store_name,boss,email,address,type ,CheckState.WaitToCheck);
        adminDao.save(newRecord);
        return ResultMessage.SUCCESS;
    }

    @Override
    @Transactional
    public ResultMessage passApply(long id) {
        Record record=adminDao.getOne(id);
        record.setCheckTime(new Date());
        record.setState(CheckState.Pass);
        adminDao.saveAndFlush(record);
        storeBLService.updateStoreState(record.getStore_id(), UserState.Valid);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage refuseApply(long id) {
        Record record=adminDao.getOne(id);
        record.setCheckTime(new Date());
        record.setState(CheckState.Refuse);
        adminDao.saveAndFlush(record);
        storeBLService.updateStoreState(record.getStore_id(), UserState.BeRefused);
        return ResultMessage.SUCCESS;
    }

    @Override
    public List<Record> getWaitCheckRecords() {
        return adminDao.getWaitCheckRecords();
    }

    @Override
    public List<Record> getDoneRecords() {
        return adminDao.getDoneRecords();
    }
}
