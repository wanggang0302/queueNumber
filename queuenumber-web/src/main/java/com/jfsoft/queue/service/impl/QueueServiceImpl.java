package com.jfsoft.queue.service.impl;

import com.jfsoft.queue.core.QueueCenter;
import com.jfsoft.queue.entity.PerCheckinfo;
import com.jfsoft.queue.factory.QueueCenterFactory;
import com.jfsoft.queue.service.IQueueService;
import com.jfsoft.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> addPerCheckinfo(String queueCode, PerCheckinfo perCheckinfo) {

        Map<String, Object> result = new HashMap<String, Object>();

        boolean isExist = false;

        //判断是否在此队列中排队
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        if(null!=queueCenter) {
            List<PerCheckinfo> perCheckinfoList = queueCenter.getPerCheckinfoList();
            for(PerCheckinfo p : perCheckinfoList) {
                if(!StringUtils.isBlank(p.getTestno()) && p.getTestno().equals(perCheckinfo.getTestno())) {
                    isExist = true;
                }
            }
        }

        if(!isExist) {
            //将体检者加入队列
            queueCenterFactory.create(queueCode, perCheckinfo);
            result.put("status", Constants.RETURN_STATUS_SUCCESS);
            result.put("data", "添加成功！");
        } else {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "已经加入过此队列，不能重复加入！");
        }

        return result;
    }

    public boolean deletePerCheckinfo(String queueCode, PerCheckinfo perCheckinfo) {

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        boolean removeState = queueCenter.remove(perCheckinfo);
        logger.debug("Removing PerCheckinfo[{}] from Queue[{}], state is {}.", perCheckinfo.getTestno(), queueCode, removeState);

        return removeState;
    }

    public boolean movePerCheckinfo(String queueCode, String testnoUp, PerCheckinfo testnoDown) {

        

        return true;
    }

}
