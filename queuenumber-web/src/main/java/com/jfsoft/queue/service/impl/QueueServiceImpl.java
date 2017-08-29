package com.jfsoft.queue.service.impl;

import com.jfsoft.queue.core.QueueCenter;
import com.jfsoft.queue.entity.PerCheckinfo;
import com.jfsoft.queue.factory.QueueCenterFactory;
import com.jfsoft.queue.service.IQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 队列业务接口实现类
 */
@Service
public class QueueServiceImpl implements IQueueService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 队列工厂
     */
    @Autowired
    private QueueCenterFactory queueCenterFactory;

    public List<PerCheckinfo> findPerCheckinfoListOfQueue(String queueCode) {

        logger.debug("findPerCheckinfoListOfQueue");

        List<PerCheckinfo> perCheckinfoList = new ArrayList<PerCheckinfo>();

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        logger.debug("queueCenter is :" + queueCenter);

        if(null!=queueCenter) {
            //获得队列中的体检者
            perCheckinfoList = queueCenter.getPerCheckinfoList();
        }
        logger.debug("perCheckinfoList size is :" + perCheckinfoList.size());

        return perCheckinfoList;
    }

    public void addPerCheckinfo(String queueCode, PerCheckinfo perCheckinfo) {
        //体检者队列
        queueCenterFactory.create(queueCode, perCheckinfo);
    }

}
