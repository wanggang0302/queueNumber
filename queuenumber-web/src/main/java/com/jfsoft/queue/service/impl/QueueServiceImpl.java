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

import java.util.*;

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

    public synchronized boolean movePerCheckinfo(String queueCode, String testnoUp, String testnoDown) throws Exception {

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        List<PerCheckinfo> oldList = queueCenter.getPerCheckinfoList();

        //调整队列顺序
        List<PerCheckinfo> newList = movePerCheckinfo(oldList, testnoUp, testnoDown);

        if(null!=newList) {
            //更新队列
            return queueCenter.update(newList);
        }

        return false;
    }

    /**
     * 调整list顺序
     */
    private List<PerCheckinfo> movePerCheckinfo(List<PerCheckinfo> oldList, String testnoUp, String testnoDown) throws Exception {

        int indexUp = -1;
        int indexDown = -1;

        PerCheckinfo perCheckinfoUp = null;
        PerCheckinfo perCheckinfoDown = null;

        for(int i=0;i<oldList.size();i++) {
            PerCheckinfo p = oldList.get(i);
            String testno = p.getTestno();
            if(testno.equals(testnoUp)) {
                indexUp = i;
                perCheckinfoUp = p;
                continue;
            } else if(testno.equals(testnoDown)) {
                indexDown = i;
                perCheckinfoDown = p;
                continue;
            }
            if(indexUp>=0 && indexDown>=0) {
                break;
            }
        }

        if(indexUp>=0 && indexDown>=0) {
            oldList.set(indexUp, perCheckinfoDown);
            oldList.set(indexDown, perCheckinfoUp);
        } else {
            logger.error("movePerCheckinfo error! indexUp is {}, indexDown is {}.", indexUp, indexDown);
            return null;
        }

        return oldList;
    }

    /**
     * 更新体检状态
     */
    public boolean updatePerCheckinfoState(String queueCode, String testno, String state) throws Exception {

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        List<PerCheckinfo> perCheckinfoList = queueCenter.getPerCheckinfoList();

        if(null!=perCheckinfoList && perCheckinfoList.size()>0) {
            for(PerCheckinfo p : perCheckinfoList) {
                if(null!=p && !StringUtils.isBlank(p.getTestno())
                        && p.getTestno().equals(testno)) {
                    p.setState(state);
                    break;
                }
            }

            queueCenter.update(perCheckinfoList);
            return true;
        }
        return false;
    }

}
