package com.bl;

import com.blservice.StoreBLService;
import com.dao.StoreDao;
import com.model.Good;
import com.model.Store;
import com.util.MailUtil;
import com.util.ResultMessage;
import com.util.StoreType;
import com.util.UserState;
import com.vo.StoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

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
                    "",province, city, area, detail_address, UserState.ToCheck,null,null);
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
    @Transactional
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

    @Override
    public List<StoreInfo> getStoreListInSameCity(String cityName){
        List<Store> stores = storeDao.findValidStoreInSameCity(cityName);
        if(stores!=null){
            List<StoreInfo> storeInfos = new ArrayList<>();
            for(Store s: stores){
                storeInfos.add(new StoreInfo(s.getId(),s.getName(),s.getIntroduce()));
            }
            return storeInfos;
        }else {
            return null;
        }
    }

    @Override
    public ResultMessage modifyStoreInfo(String store_id, String store_name, StoreType store_type, String store_boss,
                                         String contact, String province, String city, String area, String detail_address){
        Store store = storeDao.find(store_id);
        store.setName(store_name);
        store.setType(store_type);
        store.setBoss(store_boss);
        store.setEmail(contact);
        store.setProvince(province);
        store.setCity(city);
        store.setArea(area);
        store.setDetail(detail_address);

        store.setState(UserState.ToModify);
        storeDao.saveAndFlush(store);
        return ResultMessage.SUCCESS;
    }

    @Override
    public Map<String,List<Good>> getStoreGoods(String store_id){
        Store store = storeDao.find(store_id);
        if(store.getFood_types().equals("")){
            return null;
        }
        //将店铺货物分类返回
        Map<String, List<Good>> result = new HashMap<>();
        String[] storeTypes = store.getFood_types().split(",");//,分隔形式为 xxx，xxx，xxx
        for(String type: storeTypes){
            result.put(type, new ArrayList<>());
        }
        List<Good> goods = store.getGoods();
        String key_type;
        for(Good good: goods){
            key_type = good.getType();
            result.get(key_type).add(good);
        }
        return result;
    }

    @Override
    public ResultMessage addNewGoodType(String store_id, String newType) {
        String foodType = storeDao.getStoreFoodType(store_id);
        if(foodType.equals("")) {
            storeDao.updateStoreFoodType(store_id, newType);
            return ResultMessage.SUCCESS;
        }
        String[] types=foodType.split(",");
        boolean exist = false;
        for(String s: types){//判断是否有重复分类
            if(s.equals(newType)){
                exist = true;
                break;
            }
        }
        if(exist){
            return ResultMessage.EXIST;
        }else{
            storeDao.updateStoreFoodType(store_id,foodType+","+newType);
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage addNewGood(String store_id, String name, String description, double price, int amount, String type, Date start_time, Date end_time) {
        Store store = storeDao.find(store_id);
        List<Good> goods = store.getGoods();
        Good newGood = new Good(0,name,description,price,amount,type,start_time,end_time);
        goods.add(newGood);
        store.setGoods(goods);
        storeDao.saveAndFlush(store);
        return ResultMessage.SUCCESS;
    }

}
