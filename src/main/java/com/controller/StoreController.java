package com.controller;

import com.blservice.StoreBLService;
import com.model.Store;
import com.vo.StoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
