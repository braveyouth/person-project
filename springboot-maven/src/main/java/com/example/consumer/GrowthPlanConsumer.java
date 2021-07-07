package com.example.consumer;

import com.example.domain.GrowthPlanInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangy
 * @Time 2020-12-11 23:13
 */
@Component
@RabbitListener(queues = "growthplan")
public class GrowthPlanConsumer {

    private static final Logger logger = LoggerFactory.getLogger(GrowthPlanConsumer.class);

    @RabbitHandler
    public void growthPlan(GrowthPlanInfo growthPlanInfo){
        logger.info("开始消费消息..."+System.currentTimeMillis());
        logger.info("消费消息: "+growthPlanInfo.getName()+growthPlanInfo.getYear());
        logger.info("结束消费消息..."+System.currentTimeMillis());
    }
}
