package com.lw.consistenthash;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuwei
 * @date 2020-04-10 15:31
 */
@Data
public class RealNode implements WebNode{

    private String ip;

    private AtomicInteger requestCount = new AtomicInteger(0);

    public RealNode(String ip){
        this.ip = ip;
    }

    public void service() {
        requestCount.getAndIncrement();
    }

    @Override
    public Integer getCount() {
        return requestCount.get();
    }

    public String toString() {
        return "{ip: " + ip + ", requestCount: " + requestCount.intValue() + "}";
    }
}
