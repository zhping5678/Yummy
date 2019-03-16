package com.bl;

import com.blservice.StatisticsBLService;
import com.dao.OrderDao;
import com.model.Orders;
import com.util.StoreType;
import com.view.CustomerView;
import com.vo.AdminStatistics;
import com.vo.CustomerStatistics;
import com.vo.StoreStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.util.StoreType.*;

@Service
public class StatisticsBL implements StatisticsBLService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public CustomerStatistics getCustomerStatistics(String customer) {
        StoreType[] types={
                HotFood,
                FruitAndVegetable, //果蔬生鲜
                Market,//商店超市
                FlowerAndPlant,//鲜花绿植
                HealthAndMedicine,//医药健康
                Other};
        List<CustomerView> views=orderDao.getCustomerViews(customer);
        int completeNums=0,cancelNums=0,compensateNums=0;
        double completeCost=0, compensateCost=0;
        int[] orderNumsPerCost={0,0,0,0};//<=20, 20<x<=30, 30<x<=40, >40
        //不同类型店铺的消费 食物/超市/……
        int[] ordersNumPerStoreType={0,0,0,0,0,0};
        CustomerStatistics statistics=new CustomerStatistics();
        for(CustomerView view:views){
            switch (view.getState()){
                case Over:
                    completeNums++;
                    completeCost+=view.getMoney();
                    break;
                case CancelBeforeAccept:
                    cancelNums++;
                    break;
                case CancelAfterAccept:
                    compensateNums++;
                    compensateCost+=view.getMoney();
                    break;
                    default:
                        break;
            }
            double money=view.getMoney();
            if(money<=20){
                orderNumsPerCost[0]++;
            }else if(money<=30){
                orderNumsPerCost[1]++;
            }else if(money<=40){
                orderNumsPerCost[2]++;
            }else{
                orderNumsPerCost[3]++;
            }
            int index=0;
            for(int i=0;i<types.length;i++){
                if(types[i]==view.getStoreType()){
                    index=i;
                    break;
                }
            }
            ordersNumPerStoreType[index]++;
        }
        statistics.setOverOrdersNum(completeNums);
        statistics.setCancelOrdersNum(cancelNums);
        statistics.setCancelPayNum(compensateNums);
        statistics.setOverOrdersConsume(completeCost);
        statistics.setPayForCancelOrders(compensateCost*0.3);
        statistics.setOrderNumsPerPrice(orderNumsPerCost);
        statistics.setOrdersNumPerStoreType(ordersNumPerStoreType);
        return statistics;
    }

    @Override
    public StoreStatistics getStoreStatistics(String store_id) {
        List<Orders> orders=orderDao.getAllOrders(store_id);
        int completeOrderNums=0,refuseOrderNums=0,cancelBeforeAcceptNums=0,cancelAfterAcceptNum=0;
        double completeIncome=0;//抽成前收益
        double compensate=0;//抽成前赔付

        int[] timeLines={8, 11, 15, 19, 22};
        //  8:00-11:00     11:00-15:00     15:00-19：00     19：00-22:00
        int[] orderTime={0, 0, 0, 0};//点餐时间段

        int[] costLine={20,30,40,50};
        //<20  20-30  30-40 40-50 >50
        int[] orderCost={0, 0, 0, 0, 0};//订单的消费金额分布

        //真正完成的 接单后取消的 接单前取消的 被拒单的
//        int[] customerNums={0,0,0,0};//在本餐厅消费过的会员数
        ArrayList<String> over=new ArrayList<>();
        ArrayList<String> after=new ArrayList<>();
        ArrayList<String> before=new ArrayList<>();
        ArrayList<String> refuse=new ArrayList<>();

        String c;
        for(Orders order:orders){
            c=order.getCustomer();
            switch (order.getState()){
                case Over:
                    completeOrderNums++;
                    completeIncome+=order.getMoney();
                    if(!over.contains(c)){
                        over.add(c);
                    }
                    break;
                case Refuse:
                    refuseOrderNums++;
                    if(!refuse.contains(c)){
                        refuse.add(c);
                    }
                    break;
                case CancelBeforeAccept:
                    cancelBeforeAcceptNums++;
                    if(!before.contains(c)){
                        before.add(c);
                    }
                    break;
                case CancelAfterAccept:
                    cancelAfterAcceptNum++;
                    compensate+=order.getMoney();
                    if(!after.contains(c)){
                        after.add(c);
                    }
                    break;
                    default:
                        break;
            }

            Calendar calendar=Calendar.getInstance();
            calendar.setTime(order.getDate());
            int hour=calendar.get(Calendar.HOUR_OF_DAY);
            for(int i=0;i<timeLines.length-1;i++){
                if((hour>=timeLines[i])&&(hour<timeLines[i+1])){
                    orderTime[i]++;
                    break;
                }
            }

            int j;
            for( j=0;j<costLine.length;j++){
                if(order.getMoney()<=costLine[j]){
                    break;
                }
            }
            orderCost[j]++;
        }
        int[] customerNums={over.size(), after.size(), before.size(), refuse.size()};
        StoreStatistics statistics=new StoreStatistics();
        statistics.setCompleteOrderNums(completeOrderNums);
        statistics.setCancelAfterAcceptNums(cancelAfterAcceptNum);
        statistics.setCancelBeforeAcceptNums(cancelBeforeAcceptNums);
        statistics.setRefuseOrderNums(refuseOrderNums);
        statistics.setCompleteIncome(completeIncome);
        statistics.setCompensate(compensate);
        statistics.setOrderCost(orderCost);
        statistics.setOrderTime(orderTime);
        statistics.setCustomerNums(customerNums);
        return statistics;
    }

    @Override
    public AdminStatistics getAdminStatistics() {
        return null;
    }
}
