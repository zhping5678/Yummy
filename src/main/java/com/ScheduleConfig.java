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
import java.util.Scanner;
import java.util.concurrent.Executor;

//@Configuration
//@EnableAsync
public class ScheduleConfig {

    private int corePoolSize = 10;
    private int maxPoolSize = 200;
    private int queueCapacity = 10;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String input=in.nextLine();
        char[] chars=input.toCharArray();
        in.close();
    }
    public static int calculateBottle(int n,int total){
        if(n<2){
            return total;
        }else if(n==2){
            return total+1;
        }else{
            int a=n/3;
            int b=n%3;
            return calculateBottle( a+b,a+total);
        }
    }

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
