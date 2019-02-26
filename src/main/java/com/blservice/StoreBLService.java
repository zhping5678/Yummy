package com.blservice;

import com.model.Store;
import com.util.ResultMessage;
import com.util.StoreType;
import com.vo.StoreInfo;

import java.util.List;

public interface StoreBLService {

    ResultMessage applyNewStore(String store_id, String store_name, String store_boss, String store_email, String store_pass,
                                StoreType store_type, String province, String city, String area, String detail_address);

    ResultMessage storeLogin(String username, String password);

    Store getStoreInfo(String id);

    void modifyIntroduce(String id, String introduce);

    ResultMessage modifyPassword(String id, String oldPass, String newPass);

    ResultMessage findBackPass(String store_id);

    List<StoreInfo> getStoreListInSameCity(String cityName);
}
