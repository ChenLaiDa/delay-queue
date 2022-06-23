package com.example.redisZset.service;


import com.example.redisZset.utils.CalendarUtils;
import com.example.redisZset.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisDelayPlan {

	@Autowired
	private RedisUtils redis;
	private String key = "ORDER_KEY";
	/**
	 *  生产者      向redis中新增任务   下单
	 **/
	public void product(){
		String orderId="OIDNO1000";
		for (int i = 0; i < 5; i++) {
			redis.addItem(key, CalendarUtils.getCurrentTimeInMillis(10), orderId+String.format("%1$02d", i));
		}
	}

	/**
	 *  消费      扫描redis中的需要处理的订单
	 **/
	public void consumer(){
		redis.dofind(key);
	}
}
