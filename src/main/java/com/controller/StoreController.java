package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Controller
public class StoreController {

    @RequestMapping(value = "/getNextStoreId", method = RequestMethod.POST)
    public @ResponseBody String getNextStoreId(){
        try{
            File file = new File("counter.txt");
            if(file.exists()){
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//            String line = reader.readLine();
                int count = Integer.valueOf(reader.readLine());
                StringBuilder nextId = new StringBuilder(String.valueOf(count + 1));
                while (nextId.length()<7){
                    nextId.insert(0, "0");
                }
                reader.close();
                return nextId.toString();
            }else{
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write("0");
                bufferedWriter.flush();
                bufferedWriter.close();
                return "0000001";
            }
        }catch (IOException e){
            e.printStackTrace();
            return "-1";
        }
    }
}
