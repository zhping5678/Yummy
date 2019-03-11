package com;

import com.model.Account;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;

//@Configuration
//@EnableAsync
public class ScheduleConfig {

    private int corePoolSize = 10;
    private int maxPoolSize = 200;
    private int queueCapacity = 10;

//    public static void main(String[] args){
//        System.out.println(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+" "+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
//        Account account=new Account("57832579357934",90);
//        JSONObject jsonObject=JSONObject.fromObject(account);
//        System.out.println(jsonObject.toString());
//    }

//    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }
}
