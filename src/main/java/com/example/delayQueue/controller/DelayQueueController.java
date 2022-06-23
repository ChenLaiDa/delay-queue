package com.example.delayQueue.controller;

import com.example.delayQueue.service.DelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/6/23 19:37
 */
@RestController
public class DelayQueueController {
    @Autowired
    private DelayService delayService;

    @GetMapping("/saveData2DelayQueue")
    public Map<String,Object> saveData2DelayQueue(String data,Long expire){
        delayService.save(data, expire);
        Map<String, Object> result = new HashMap<>();
        result.put("200","success");
        return result;
    }
}
