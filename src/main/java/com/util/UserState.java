package com.util;

public enum UserState{
    //普通消费者的状态
    WaitToActive,//待激活
    CloseAccount,//账号已经注销

    //平台店铺状态
    ToCheck,//待审核状态
    ToModify,//店铺信息更改审核中
    BeRefused,//审核申请被驳回

    Valid//账号正使用中
}