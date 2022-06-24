package com.example.mqDelay;

import com.example.redisZset.utils.CalendarUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/6/24 12:05
 */
@RestController
@RequestMapping("/mq")
@Slf4j
public class MQDelayController {
    @Resource
    private AmqpTemplate amqpTemplate;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Map<String,Object> add(){
        Map<String,Object> result = new HashMap<>();
        String orderId = "1010101";
        for (int i = 0; i < 10; i++) {
            //创建订单
            amqpTemplate.convertAndSend(MQProperties.EXCHANGE_NAME, MQProperties.ROUTE_KEY, orderId+i);
            log.info(CalendarUtils.getCurrentTimeByStr(0)+" 生成了一个订单，订单ID："+orderId+i);
            if(i%3==0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        result.put("200","success");
        return result;

    }
}
