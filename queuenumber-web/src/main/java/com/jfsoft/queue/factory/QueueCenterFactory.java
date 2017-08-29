package com.jfsoft.queue.factory;

import com.jfsoft.queue.core.QueueCenter;
import com.jfsoft.queue.entity.PerCheckinfo;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 队列工厂类，用户产生、获得队列
 */
@Component
public class QueueCenterFactory {

    //队列盒子
    private ConcurrentHashMap<String, QueueCenter> queueCenterMap;

    /**
     * 构造方法
     */
    public QueueCenterFactory() {
        this.queueCenterMap = new ConcurrentHashMap<String, QueueCenter>();
    }

    /**
     * 创建队列
     */
    public void create(String queueCenterKey, PerCheckinfo perCheckinfo) {
        if(!queueCenterMap.containsKey(queueCenterKey)) {
            QueueCenter queueCenter = new QueueCenter();
            queueCenter.produce(perCheckinfo);
            this.queueCenterMap.put(queueCenterKey, queueCenter);
        }
    }

    /**
     * 获得队列
     */
    public QueueCenter obtain(String queueCenterKey) {

        QueueCenter queueCenter = null;
        if(queueCenterMap.containsKey(queueCenterKey)) {
            queueCenter = queueCenterMap.get(queueCenterKey);
        }

        return queueCenter;
    }

}
