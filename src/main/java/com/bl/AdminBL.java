package com.bl;

import com.blservice.AdminBLService;
import com.blservice.StoreBLService;
import com.dao.AdminDao;
import com.model.Record;
import com.util.CheckState;
import com.util.ResultMessage;
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
    public ResultMessage newApply(String store_id) {
        Record newRecord=new Record(0,new Date(),null,store_id, CheckState.WaitToCheck);
        adminDao.save(newRecord);
        return ResultMessage.SUCCESS;
    }

    @Override
    @Transactional
    public ResultMessage passApply(long id, String store_id) {
        storeBLService.updateStoreState(store_id, UserState.Valid);
        Record record=adminDao.getOne(id);
        record.setCheckTime(new Date());
        record.setState(CheckState.Pass);
        adminDao.saveAndFlush(record);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage refuseApply(long id, String store_id) {
        storeBLService.updateStoreState(store_id, UserState.BeRefused);
        Record record=adminDao.getOne(id);
        record.setCheckTime(new Date());
        record.setState(CheckState.Refuse);
        adminDao.saveAndFlush(record);
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
