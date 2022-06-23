package com.example.redisZset.controller;

import com.example.redisZset.service.RedisDelayPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/6/23 21:11
 */
@RequestMapping("/zSet")
@RestController
public class PlanController {
    @Autowired
    private RedisDelayPlan redisDelayPlan;

    @RequestMapping("/add")
    public Map<String ,Object> add(){
        redisDelayPlan.product();
        Map<String,Object> result = new HashMap<>();
        result.put("200","success");
        return result;
    }
}
