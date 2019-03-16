package com.controller;

import com.blservice.StatisticsBLService;
import com.blservice.StoreBLService;
import com.model.Good;
import com.model.Store;
import com.util.ResultMessage;
import com.util.StoreType;
import com.vo.StoreInfo;

import com.vo.StoreStatistics;
import com.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class StoreController {

    @Autowired
    private StoreBLService storeBLService;
    @Autowired
    private StatisticsBLService statisticsBLService;

    @RequestMapping(value = "/getNextStoreId", method = RequestMethod.POST)
    public @ResponseBody String getNextStoreId(){
        try{
            File file = new File("counter.txt");
            if(file.exists()){
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//            String line = reader.readLine();
                int count = Integer.valueOf(reader.readLine());
//                System.out.println("controller 中读取数值: "+count);
                StringBuilder nextId = new StringBuilder(String.valueOf(count + 1));
                while (nextId.length()<7){
                    nextId.insert(0, "0");
                }
                reader.close();
                return nextId.toString();
            }else{
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(0);
                bufferedWriter.flush();
                bufferedWriter.close();
                return "0000001";
            }
        }catch (IOException e){
            e.printStackTrace();
            return "-1";
        }
    }

    @RequestMapping(value = "/applyStore", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage applyNewStore(String store_id, String store_name, String store_boss, String store_email, String store_pass,
                                StoreType store_type, String province, String city, String area, String detail_address){
        return storeBLService.applyNewStore(store_id, store_name,store_boss, store_email,store_pass,store_type,
                province,city,area,detail_address);
    }


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

    @RequestMapping(value = "/modifyStrategy", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage modifyStoreStrategy(@RequestBody Map<String,String> strategies){
        System.out.println("传过来的："+strategies);
        return storeBLService.modifyStrategy(strategies);
    }

    @RequestMapping(value = "/getStoreGood",method = RequestMethod.POST)
    public @ResponseBody
    Map<String,List<Good>> getStoreGoods(String store_id){
        return storeBLService.getStoreGoods(store_id);
    }

    @RequestMapping(value = "/getStrategies",method = RequestMethod.POST)
    public @ResponseBody
    Map<Integer,Integer> getStoreStrategies(String store_id){
        return storeBLService.getStoreStrategies(store_id);
    }

    @RequestMapping(value = "/addNewGoodType", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage addNewGoodType(String store_id, String newType){
        return storeBLService.addNewGoodType(store_id, newType);
    }

    @RequestMapping(value = "/addNewGood", method = RequestMethod.POST)
    public @ResponseBody
    Good addNewGood(String store_id, String name, String description, double price, int amount,
                    String type, Date start_time, Date end_time){
        return storeBLService.addNewGood(store_id, name,description,price,amount,type,start_time,end_time);
    }

    @RequestMapping(value = "/deleteGoodType", method = RequestMethod.POST)
    public @ResponseBody
    void deleteGoodType(String store_id,String toDelete){
        System.out.println("要删除的分类："+toDelete);
        storeBLService.deleteGoodType(store_id, toDelete);
    }

    @RequestMapping(value = "/withdrawGood", method = RequestMethod.POST)
    public @ResponseBody
    String withdrawGood(String store_id,String good_id){
        return storeBLService.withdrawGood(store_id,good_id);
    }

    @RequestMapping(value = "/modifyGoodInfo", method = RequestMethod.POST)
    public @ResponseBody
    void modifyGoodInfo(long good_id, String name, String description, double price, int amount,
                        Date start_time, Date end_time){
        storeBLService.modifyGoodInfo(good_id, name,description,price,amount,start_time,end_time);
    }

    @RequestMapping(value = "/getGoodInfo", method = RequestMethod.POST)
    public @ResponseBody
    Good getGoodInfoById(long good_id){
        return storeBLService.findGoodInfoById(good_id);
    }

    @RequestMapping(value = "/getStoreVo", method = RequestMethod.POST)
    public @ResponseBody
    StoreVO getStoreVO(String store_id){
        return storeBLService.getStoreVO(store_id);
    }

    @RequestMapping(value = "/getStoreStatistics",method = RequestMethod.POST)
    public @ResponseBody
    StoreStatistics getStoreStatistics(String store_id){
        return statisticsBLService.getStoreStatistics(store_id);
    }
}
