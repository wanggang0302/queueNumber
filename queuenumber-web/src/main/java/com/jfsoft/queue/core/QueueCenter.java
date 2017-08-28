package com.jfsoft.queue.core;

import com.jfsoft.model.CallingDevice;
import com.jfsoft.queue.entity.PerCheckinfo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 代表一个服务队列
 * wanggang
 * 2017年8月28日
 */
public class QueueCenter {

    //医生最大数量（需要从数据库读取）
    private final static int MAXCOUNT = 10;

    //呼号器队列（即体检医生的数量）
    private BlockingQueue<CallingDevice> callingDevices;
    //体检者队列
    private BlockingQueue<PerCheckinfo> perCheckinfos;

    public BlockingQueue<CallingDevice> getCallingDevices() {
        return callingDevices;
    }
    public BlockingQueue<PerCheckinfo> getPerCheckinfos() {
        return perCheckinfos;
    }

    public QueueCenter() {

        //创建一个科室下的医生队列
        this.callingDevices = new LinkedBlockingQueue<CallingDevice>();

        //医生工作就绪,创建体检者队列
        this.perCheckinfos = new LinkedBlockingQueue<PerCheckinfo>();

    }

}
