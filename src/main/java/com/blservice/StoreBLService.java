package com.blservice;

import com.model.Good;
import com.model.Store;
import com.util.ResultMessage;
import com.util.StoreType;
import com.vo.StoreInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StoreBLService {

    ResultMessage applyNewStore(String store_id, String store_name, String store_boss, String store_email, String store_pass,
                                StoreType store_type, String province, String city, String area, String detail_address);

    ResultMessage storeLogin(String username, String password);

    Store getStoreInfo(String id);

    void modifyIntroduce(String id, String introduce);

    ResultMessage modifyPassword(String id, String oldPass, String newPass);

    ResultMessage findBackPass(String store_id);

    List<StoreInfo> getStoreListInSameCity(String cityName);

    ResultMessage modifyStoreInfo(String store_id, String store_name, StoreType store_type, String store_boss,
                                  String contact, String province, String city, String area, String detail_address);

    Map<String,List<Good>> getStoreGoods(String store_id);

    ResultMessage addNewGoodType(String store_id, String newType);

    ResultMessage addNewGood(String store_id, String name, String description, double price, int amount,
                             String type, Date start_time, Date end_time);
}
