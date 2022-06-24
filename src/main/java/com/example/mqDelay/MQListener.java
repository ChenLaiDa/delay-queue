package com.example.mqDelay;

import com.example.redisZset.utils.CalendarUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/6/24 12:09
 */
@Component
@Slf4j
public class MQListener {

    @RabbitListener(queues = MQProperties.DEAD_QUEUE_NAME)
    public void process(String message) throws IOException {
        log.info(CalendarUtils.getCurrentTimeByStr(0)+" 消费了一个超时订单，订单ID："+message);

    }
}
