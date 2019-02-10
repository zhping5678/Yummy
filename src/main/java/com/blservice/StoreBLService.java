package com.blservice;

import com.util.ResultMessage;
import com.util.StoreType;

public interface StoreBLService {

    ResultMessage applyNewStore(String store_id, String store_name, String store_boss, String store_email, String store_pass,
                                StoreType store_type, String province, String city, String area, String detail_address);

    ResultMessage storeLogin(String username, String password);
}
