package com.jfsoft.queue.service;

import com.jfsoft.queue.entity.PerCheckinfo;

import java.util.List;
import java.util.Map;

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
    public Map<String, Object> addPerCheckinfo(String queueCode, PerCheckinfo perCheckinfo);

    /**
     * 从队列中移除某个体检者
     */
    public boolean deletePerCheckinfo(String queueCode, PerCheckinfo perCheckinfo);

}
