package com.example.delayQueue.service;

import com.example.delayQueue.model.DelayedItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/6/23 19:20
 */
@Service
@Slf4j
public class DelayService {
    /**
     * 静态变量设置一个延迟队列
     */
    private static DelayQueue<DelayedItem<String>> delayQueue = new DelayQueue<>();

    /**
     * 提供一个方法外部可以添加数据到延迟队列
     */
    public void save(String data,Long expireTime){
        DelayedItem<String> delayedItem = new DelayedItem<>(data, expireTime);
        //添加到延迟队列
        delayQueue.put(delayedItem);
        log.info("此数据【超时时间:"+expireTime+"毫秒】被推入延时队列,数据详情: " + data + ",当前时间: 【"+
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                +"】" );
    }

    /**
     * 异步线程处理延时队列
     */
    class DelayTask implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    DelayedItem<String> delayedItem = delayQueue.take();
                    String data = delayedItem.getData();
                    log.info("数据【"+data+"】被消费,"+"当前时间: 【"+
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                            +"】" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //被 @PostConstruct 修饰的方法,会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。

    /**
     * 服务器启动时开启上面的异步线程
     */
    @PostConstruct
    public void init(){
        LocalDateTime now = LocalDateTime.now();

        new Thread(new DelayTask()).start();
        log.warn("【【【异步线程延时队列开启完毕】】】");
    }





}
