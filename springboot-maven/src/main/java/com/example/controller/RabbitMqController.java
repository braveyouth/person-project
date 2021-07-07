package com.example.controller;

import com.example.domain.GrowthPlanInfo;
import com.example.producer.TaskProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangy
 * @Time 2020-12-09 23:14
 */
@RestController
@RequestMapping("/test")
public class RabbitMqController {

    @Autowired
    private TaskProducer taskProducer;

    @GetMapping("/hello")
    public String hello(){
        String text="hello";
        taskProducer.sendHelloTask(text);
        return text;
    }

    @GetMapping("/growthplan")
    public String growthPlan(){
        GrowthPlanInfo growthPlanInfo = new GrowthPlanInfo();
        growthPlanInfo.setName("成长计划");
        growthPlanInfo.setYear(2021);
        taskProducer.sendGrowthPlanTask(growthPlanInfo);
        return "growthplan";
    }
}
