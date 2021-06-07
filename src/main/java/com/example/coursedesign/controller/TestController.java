package com.example.coursedesign.controller;


import com.alibaba.fastjson.JSON;
import com.example.coursedesign.entity.Mydate;
import com.example.coursedesign.mapper.Usermapper;
import com.example.coursedesign.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TestController {


    public static void main(String[] args) {
//        List<Date> dates = new ArrayList<>();
//        dates.add(new Date(2021-1900,6-1,4));
//        dates.add(new Date(2021-1900,6-1,5));
//        dates.add(new Date(2021-1900,6-1,6));
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
//        dates.add(new Date(-51));
        UserService service = new UserService();
        List<Mydate> dates = service.selectPretimeByLabid(1);
        System.out.println(dates);
//        for (Date date : dates) {
//            System.out.println(date);
//        }
    }
}
