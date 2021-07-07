package com.example.producer;

import com.example.domain.GrowthPlanInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author zhangy
 * @Time 2020-12-11 20:20
 */
@Component
public class TaskProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendHelloTask(String text) {
        if (StringUtils.hasText(text)) {
            try {
                rabbitTemplate.convertAndSend("hello", text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendGrowthPlanTask(GrowthPlanInfo growthPlanInfo) {
        if (growthPlanInfo!=null) {
            try {
                rabbitTemplate.convertAndSend("growthplan", growthPlanInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
