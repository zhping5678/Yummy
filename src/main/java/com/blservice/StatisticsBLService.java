package com.blservice;

import com.vo.AdminStatistics;
import com.vo.CustomerStatistics;
import com.vo.StoreStatistics;

public interface StatisticsBLService {

    //顾客统计数据
    CustomerStatistics getCustomerStatistics(String customer);


    //店铺统计数据
    StoreStatistics getStoreStatistics(String store_id);

    //管理员统计数据
    AdminStatistics getAdminStatistics();

}
