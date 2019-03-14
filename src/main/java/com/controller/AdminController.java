package com.controller;

import com.blservice.AdminBLService;
import com.blservice.AdminStatistics;
import com.model.Record;
import com.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminBLService adminBLService;
    @Autowired
    private AdminStatistics adminStatistics;

    @RequestMapping(value = "/passApply", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage passApply(long id){
        return adminBLService.passApply(id);
    }

    @RequestMapping(value = "/refuseApply", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage refuseApply(long id){
        return adminBLService.refuseApply(id);
    }

    @RequestMapping(value = "/getWaitCheckRecords",method = RequestMethod.POST)
    public @ResponseBody
    List<Record> getWaitCheckRecords(){
        return adminBLService.getWaitCheckRecords();
    }

    @RequestMapping(value = "/getDoneRecords", method = RequestMethod.POST)
    public @ResponseBody
    List<Record> getDoneRecords(){
        return adminBLService.getDoneRecords();
    }



}
