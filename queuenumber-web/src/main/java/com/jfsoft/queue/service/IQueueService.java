package com.jfsoft.queue.service;

import com.jfsoft.model.SysQueue;
import com.jfsoft.vo.PerCheckinfo;

import java.util.List;
import java.util.Map;

/**
 * 队列操作业务接口
 */
public interface IQueueService {

    /**
     * 查询所有队列的信息
     */
    public List<SysQueue> findListOfQueue() throws Exception;

    /**
     * 查询某个队列中的全部体检者
     * @param queueCode
     * @return
     */
    public List<PerCheckinfo> findPerCheckinfoListOfQueue(String queueCode, String isVip);

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

    /**
     * 跨队列调整
     */
    public Map<String, Object> moveHorizontal(String queueCode, String testnoFromQueue, String testnoFromVipQueue) throws Exception;

    /**
     * 调整队列顺序（上下调整）
     */
    public boolean moveVertical(String queueCode, String isVip, String testnoUp, String testnoDown) throws Exception;

    /**
     * 更新体检者状态
     */
    public boolean updatePerCheckinfoState(String queueCode, PerCheckinfo perCheckinfo, String state) throws Exception;

}
