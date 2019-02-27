package com.controller;

import com.blservice.StoreBLService;
import com.model.Good;
import com.model.Store;
import com.util.ResultMessage;
import com.util.StoreType;
import com.vo.StoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class StoreController {

    @Autowired
    private StoreBLService storeBLService;

    @RequestMapping(value = "/getStoreInfo", method = RequestMethod.POST)
    public @ResponseBody
    Store getStoreInfo(String store_id){
        return storeBLService.getStoreInfo(store_id);
    }

    @RequestMapping(value = "/getStoreInfoList", method = RequestMethod.POST)
    public @ResponseBody
    List<StoreInfo> getStoreListInSameCity(String cityName){
        return storeBLService.getStoreListInSameCity(cityName);
    }

    @RequestMapping(value = "/modifyIntroduce", method = RequestMethod.POST)
    public @ResponseBody
    void modifyIntroduce(String store_id, String introduce){
        storeBLService.modifyIntroduce(store_id, introduce);
    }

    @RequestMapping(value = "/modifyInfo", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage modifyStoreInfo(String store_id, String store_name, StoreType store_type, String store_boss,
                                  String contact, String province, String city, String area, String detail_address){
        return storeBLService.modifyStoreInfo(store_id, store_name,store_type,store_boss,contact,province,city,area,detail_address);
    }

    @RequestMapping(value = "/getStoreGood",method = RequestMethod.POST)
    public Map<String,List<Good>> getStoreGoods(String store_id){
        return storeBLService.getStoreGoods(store_id);
    }

    @RequestMapping(value = "/addNewGoodType", method = RequestMethod.POST)
    public ResultMessage addNewGoodType(String store_id, String newType){
        return storeBLService.addNewGoodType(store_id, newType);
    }

    @RequestMapping(value = "/addNewGood", method = RequestMethod.POST)
    public ResultMessage addNewGoodType(String store_id, String name,String description, double price,int amount,
                                        String type, Date start_time,Date end_time){
        return storeBLService.addNewGood(store_id, name,description,price,amount,type,start_time,end_time);
    }


}
