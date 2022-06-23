package com.example.delayQueue.model;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/6/23 19:11
 */
@Data
public class DelayedItem<T> implements Delayed {

    /**
     * 过期时长/单位毫秒
     */
    private Long expireTime;
    /**
     * 目标对象
     */
    private T data;

    public DelayedItem(T data,Long expireTime) {
        super();
        this.expireTime = expireTime+System.currentTimeMillis();
        this.data = data;
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expireTime - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    public T getData() {
        return this.data;
    }
}
