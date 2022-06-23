package com.example.redisExpire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/6/23 16:20
 */
@RestController
public class RedisController {
    @Autowired
    public RedisTemplate<String,String> redisTemplate;

    @RequestMapping(value = "/redisTest", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> redisTest(){
        redisTemplate.opsForValue().set("myKey", "myValue",5, TimeUnit.SECONDS);
        String myKey = redisTemplate.opsForValue().get("myKey");
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("myKey",myKey);
        return resultMap;
    }
}
