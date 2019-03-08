package com.bl;

import com.blservice.StoreBLService;
import com.dao.GoodDao;
import com.dao.StoreDao;
import com.model.Good;
import com.model.Store;
import com.util.*;
import com.vo.StoreInfo;
import com.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

@Service
public class StoreBL implements StoreBLService {
    @Autowired
    private StoreDao storeDao;

    @Autowired
    private GoodDao goodDao;

    @Override
    public ResultMessage applyNewStore(String store_id, String store_name, String store_boss, String store_email,
                                       String store_pass, StoreType store_type, String province, String city,
                                       String area, String detail_address) {
        try {

            Store newStore = new Store(store_id, store_name, store_boss, store_pass, store_email, "", store_type,
                    new ArrayList<>(),province, city, area, detail_address, UserState.ToCheck,null,null);
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
    public ResultMessage modifyStrategy(Map<String, String> strategies) {
        String store_id=strategies.get("store_id");
        Store store = storeDao.find(store_id);
        Map<Integer,Integer> storeStrategy=new HashMap<>();
        for(Map.Entry<String,String> entry:strategies.entrySet()){
            if(!entry.getKey().equals("store_id")){
                System.out.println("满："+entry.getKey()+"减："+entry.getValue());
                storeStrategy.put(Integer.valueOf(entry.getKey()),Integer.valueOf(entry.getValue()));
            }
        }
        store.setDiscounts(storeStrategy);
        storeDao.saveAndFlush(store);
        return ResultMessage.SUCCESS;
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
        System.out.println(store.toString());
        if(store.getFood_types().size()==0 &&( store.getGoods()==null||store.getGoods().size()==0)){
            return null;
        }
        //将店铺货物分类返回
        Map<String, List<Good>> result = new HashMap<>();
        List<String> storeTypes = store.getFood_types();
        for(String type: storeTypes){
            result.put(type, new ArrayList<>());
        }
        result.put("已下架商品",new ArrayList<>());
        List<Good> goods = store.getGoods();
        String key_type;
        for(Good good: goods){
            if(good.getState()!= GoodState.InValid){
                key_type = good.getType();
                result.get(key_type).add(good);
            }else{
                result.get("已下架商品").add(good);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getStoreStrategies(String store_id) {
        return storeDao.find(store_id).getDiscounts();
    }

    @Override
    public ResultMessage addNewGoodType(String store_id, String newType) {
        Store store=storeDao.find(store_id);
        List<String> foodType = store.getFood_types();
        if(!foodType.contains(newType)) {
            foodType.add(newType);
            store.setFood_types(foodType);
            storeDao.saveAndFlush(store);
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.EXIST;
        }
    }

    @Override
    public Good addNewGood(String store_id, String name, String description, double price, int amount,
                           String type, Date start_time, Date end_time) {
        Store store = storeDao.find(store_id);
        List<Good> goods = store.getGoods();
        GoodState state;
        if(start_time.after(new Date())){
            state = GoodState.Wait;
        }else{
            state = GoodState.Valid;
        }
        Good newGood = new Good(0,name,description,price,amount,type,start_time,end_time,state);
        goods.add(newGood);
        store.setGoods(goods);
        storeDao.saveAndFlush(store);
        return newGood;
    }

    @Override
    public void deleteGoodType(String store_id, String toDeleteType){
        Store store = storeDao.find(store_id);
        List<String> good_types = store.getFood_types();
        good_types.remove(toDeleteType);
        store.setFood_types(good_types);
        List<Good> goods = store.getGoods();
        for(Good good: goods){
            if(good.getType().equals(toDeleteType)){
                good.setState(GoodState.InValid);
            }
        }
        store.setGoods(goods);
        System.out.println("删除分类后的store内容："+store);
        storeDao.saveAndFlush(store);
    }

    @Override
    public String withdrawGood(String store_id, String good_id){
        String type="";
        Store store = storeDao.find(store_id);
        List<Good> goods = store.getGoods();
        for(Good good:goods){
            if(good.getId()==Integer.valueOf(good_id)){
                good.setState(GoodState.InValid);
                good.setEnd_time(new Date());
//                good.setEnd_time(DateFormat.getDateInstance(DateFormat.DEFAULT).format(new Date()));
                type=good.getType();
                break;
            }
        }
        store.setGoods(goods);
        System.out.println("下架某商品后");
        storeDao.saveAndFlush(store);
        return type;
    }

    @Override
    public Good findGoodInfoById(long good_id) {
        Good good=goodDao.find(good_id);
        System.out.println("good: "+good);
        return good;
    }

    @Override
    public void modifyGoodInfo(long good_id, String name, String description, double price, int amount,
                               Date start_time, Date end_time) {
        Good good = goodDao.getOne(good_id);
        good.setName(name);
        good.setDescription(description);
        good.setPrice(price);
        good.setAmount(amount);
        good.setStart_time(start_time);
        good.setEnd_time(end_time);
        if(start_time.before(new Date())){
            good.setState(GoodState.Valid);
        }else{
            good.setState(GoodState.Wait);
        }
        goodDao.saveAndFlush(good);
    }

    @Override
    public StoreVO getStoreVO(String store_id){
        Store store = storeDao.find(store_id);
        StoreVO storeVO=new StoreVO(store.getId(),store.getName(),store.getBoss(),store.getEmail(),store.getIntroduce(),
                store.getType().toString(),store.getFood_types(),store.getProvince(),store.getCity(),store.getArea(),store.getDetail());
//        Map<Integer,Integer> discounts = store.getDiscounts();
//        StringBuilder discount= new StringBuilder();
//        for(Map.Entry<Integer,Integer> entry:discounts.entrySet()){
//            discount.append("满").append(entry.getKey()).append("减").append(entry.getValue()).append(" ");
//        }
        storeVO.setDiscounts(store.getDiscounts());
        //将正在售卖的商品按商品分类返回
        Map<String,List<Good>> goods = new HashMap<>();
        for(String type: store.getFood_types()){
            goods.put(type,new ArrayList<>());
        }
        String type;
        for(Good good:store.getGoods()){
            if(good.getState()==GoodState.Valid){
                type=good.getType();
                goods.get(type).add(good);
            }
        }
        storeVO.setGoods(goods);
        return storeVO;
    }

    /*
     * 每天零点检查食物是否上架
     */
    @Scheduled(cron = "0 5 0 * * ?")
//    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void updateGoodState(){
        System.out.println("检查食物是否上架，是否到期下架");
        int num1=goodDao.updateWaitGood(new Date());
        int num2=goodDao.updateValidGood(new Date());
        System.out.println("更新数量："+num1+" "+num2);
    }
}
