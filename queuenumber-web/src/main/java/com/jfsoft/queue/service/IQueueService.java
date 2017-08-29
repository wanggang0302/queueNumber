package com.jfsoft.queue.service;

import com.jfsoft.queue.entity.PerCheckinfo;

import java.util.List;

/**
 * 队列操作业务接口
 */
public interface IQueueService {

    /**
     * 查询某个队列中的全部体检者
     * @param queueCode
     * @return
     */
    public List<PerCheckinfo> findPerCheckinfoListOfQueue(String queueCode);

    /**
     * 将体检者增加到队列中
     * @param queueCode
     * @param perCheckinfo
     */
    public void addPerCheckinfo(String queueCode, PerCheckinfo perCheckinfo);

}
