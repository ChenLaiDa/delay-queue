package com.example.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/6/23 16:17
 */
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Resource
    private RedisTemplate redisTemplate;


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对 redis 数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("=== redis监听过期key ====");
        String s = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("监听过期键值==="+s);

    }
}
