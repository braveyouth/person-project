package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Year;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author zhangy
 * @Time 2020/4/4 23:13
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @RequestMapping("/sum")
    public BigDecimal sum(){
        return new BigDecimal(0.00).add(new BigDecimal(1.13));
    }

    public static void main(String[] args) {

//        TestController.getISO8601Timestamp(Date.from(Instant.now()));
        System.out.println(Year.now());
        System.out.println(Year.now().getValue());

    }

    public static String getISO8601Timestamp(Date date){
//        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        System.out.println(nowAsISO);
        return nowAsISO;
    }

}
