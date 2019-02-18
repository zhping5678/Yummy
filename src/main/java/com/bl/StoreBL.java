package com.bl;

import com.blservice.StoreBLService;
import com.dao.StoreDao;
import com.model.Store;
import com.util.MailUtil;
import com.util.ResultMessage;
import com.util.StoreType;
import com.util.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class StoreBL implements StoreBLService {
    @Autowired
    private StoreDao storeDao;

    @Override
    public ResultMessage applyNewStore(String store_id, String store_name, String store_boss, String store_email,
                                       String store_pass, StoreType store_type, String province, String city,
                                       String area, String detail_address) {
        try {

            Store newStore = new Store(store_id, store_name, store_boss, store_pass, store_email, "", store_type,
                    province, city, area, detail_address, UserState.ToCheck);
            storeDao.saveAndFlush(newStore);

            //更新counter.txt(店铺编号)
            File file = new File("counter.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            int oldCount = Integer.valueOf(bufferedReader.readLine());
//            System.out.println("BL中读取旧值： "+oldCount);
            String newCount = String.valueOf(oldCount+1);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            bufferedWriter.write(newCount);
            bufferedWriter.flush();
//            System.out.println("更新counter");
            bufferedWriter.close();

            return ResultMessage.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    @Override
    public ResultMessage storeLogin(String username, String password) {
        Store store = storeDao.find(username);
        if(store == null){
            return ResultMessage.NotExist;
        }else if(!store.getPassword().equals(password)){
            return ResultMessage.PassError;
        }else {
            return ResultMessage.StoreLogin;
        }
    }

    @Override
    public Store getStoreInfo(String id) {
        return storeDao.find(id);
    }

    @Override
    public void modifyIntroduce(String id, String introduce) {
        storeDao.updateIntroduce(id,introduce);
    }

    @Override
    public ResultMessage modifyPassword(String id, String oldPass, String newPass) {
        Store store = storeDao.find(id);
        if(!store.getPassword().equals(oldPass)){
            return ResultMessage.PassError;
        }else{
            store.setPassword(newPass);
            storeDao.saveAndFlush(store);
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage findBackPass(String store_id) {
        Store store = storeDao.find(store_id);
        String email = store.getEmail();
        if(email ==null || email.equals("")){
            return ResultMessage.NoEmail;
        }else{
            String newPass = MailUtil.getRandomPassword();
            store.setPassword(newPass);
            new Thread(new MailUtil(email, newPass, false)).start();
            storeDao.saveAndFlush(store);
            return ResultMessage.SUCCESS;
        }
    }


}
