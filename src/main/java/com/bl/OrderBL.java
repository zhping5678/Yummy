package com.bl;

import com.blservice.CustomerBLService;
import com.blservice.OrderBLService;
import com.blservice.StoreBLService;
import com.dao.OrderDao;
import com.model.GoodList;
import com.model.Orders;
import com.util.OrderState;
import com.util.ResultMessage;
import com.vo.GoodListVO;
import com.vo.OrderInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderBL implements OrderBLService {
//    @Autowired
//    private StoreDao storeDao;
    @Autowired
    private StoreBLService storeBLService;
    @Autowired
    private CustomerBLService customerBLService;
    @Autowired
    private OrderDao orderDao;

    private static final String pattern="yyyyMMddHHmmssSSS";


    @Override
    public String submitNewOrder(OrderInfo orderInfo) {
        //先检查库存是否都充足
        List<GoodListVO> goodLists=orderInfo.getList();
        List<GoodList> list=new ArrayList<>();
        for(GoodListVO goodListVO:goodLists){
            if(goodListVO.getNum()>storeBLService.getStockNum(goodListVO.getGood_id())){
                return "\""+ResultMessage.StockNotEnough.toString()+goodListVO.getGood_name()+"\"";
            }else{
                list.add(new GoodList(0,goodListVO.getGood_id(),goodListVO.getGood_name(),goodListVO.getNum(),goodListVO.getGood_price()));
            }
        }
//        System.out.println("账户余额："+customerBLService.getBalance(orderInfo.getAccount()));
        //检查账户余额是否充足
        if(orderInfo.getMoney()>customerBLService.getBalance(orderInfo.getAccount())){
            return "\""+ResultMessage.BalanceNotEnough.toString()+"\"";
        }
        Orders newOrder=new Orders(orderInfo);
        newOrder.setId(new SimpleDateFormat(pattern).format(new Date()));
        newOrder.setState(OrderState.WaitToPay);
        newOrder.setGoodLists(list);
        orderDao.save(newOrder);
        JSONObject jsonObject=JSONObject.fromObject(newOrder);
        return jsonObject.toString();
    }

    @Override
    public List<Orders> getOnOrder(String username) {
        return orderDao.getOnOrder(username);
    }

    @Override
    @Transactional
    public ResultMessage payOrder(String order_id){
        Orders order=orderDao.getOne(order_id);
        if(customerBLService.getBalance(order.getAccount())<order.getMoney()){
            return ResultMessage.BalanceNotEnough;
        }
        for(GoodList goodList:order.getGoodLists()){
            if(goodList.getAmount()>storeBLService.getStockNum(goodList.getGood_id())){
                return ResultMessage.StockNotEnough;
            }
        }

        if (orderDao.payOrder(order.getId())>0 &&
                customerBLService.accountOut(order.getAccount(),order.getMoney())>0 ){
            return ResultMessage.SUCCESS;
        }else {
            return ResultMessage.FAIL;
        }
    }

    @Override
    public ResultMessage cancelOrder(String order_id){
        Orders order=orderDao.getOne(order_id);
        if(order.getState()==OrderState.WaitToSendOut){
            customerBLService.accountIn(order.getAccount(),order.getMoney()*0.7);
            order.setState(OrderState.CancelAfterAccept);
            order.setNote("因取消订单,扣除原订单的30%,即"+order.getMoney()*0.3);
            orderDao.saveAndFlush(order);
            storeBLService.inStock(order.getGoodLists());
            return ResultMessage.RefundSuccess;
        }else if(order.getState()==OrderState.WaitToPay||order.getState()==OrderState.WaitStoreToAccept) {
            order.setState(OrderState.CancelBeforeAccept);
            orderDao.saveAndFlush(order);
            customerBLService.accountIn(order.getAccount(),order.getMoney());
            return ResultMessage.SUCCESS;
        }else {
            return ResultMessage.FAIL;
        }
    }

    @Override
    public ResultMessage confirmOrder(String order_id){
        Orders order=orderDao.getOne(order_id);
        order.setState(OrderState.Over);
        order.setArrive(new Date());
        orderDao.saveAndFlush(order);
        double sum=orderDao.getSumConsume(order.getCustomer());
        customerBLService.updateLevel(order.getCustomer(),sum);
        return ResultMessage.SUCCESS;
    }

    @Override
    public List<Orders> getHistoryOrders(String username){
        return orderDao.getHistoryOrders(username);
    }

    @Override
    public List<Orders> getStoreNewOrders(String store_id) {
        return orderDao.getStoreNewOrders(store_id);
    }

    @Override
    public List<Orders> getStorePreparingOrders(String store_id) {
        return orderDao.getStorePreparingOrders(store_id);
    }

    @Override
    public List<Orders> getStoreSendOrders(String store_id) {
        return orderDao.getStoreSendOrders(store_id);
    }

    @Override
    public List<Orders> getStoreCompleteOrders(String store_id) {
        return orderDao.getStoreCompleteOrders(store_id);
    }

    @Override
    public List<Orders> getStoreCancelOrders(String store_id) {
        return orderDao.getStoreCancelOrders(store_id);
    }

    @Override
    @Transactional
    public ResultMessage acceptOrder(String order_id) {
        Orders order=orderDao.getOne(order_id);
        if(order.getState()==OrderState.WaitStoreToAccept){
            order.setState(OrderState.WaitToSendOut);
            storeBLService.outStock(order.getGoodLists());
            return ResultMessage.SUCCESS;
        } else{
            return ResultMessage.Cancel;
        }
    }

    @Override
    @Transactional
    public ResultMessage sendOutOrder(String order_id) {
        if(orderDao.sendOutOrder(order_id)==1){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.Cancel;
        }
    }

    @Override
    @Transactional
    public ResultMessage refuseOrder(String order_id) {
        if(orderDao.refuseOrder(order_id)==1){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.Cancel;
        }
    }
}
