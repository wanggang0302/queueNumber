package com.jfsoft.doctor.service.impl;

import com.jfsoft.doctor.service.IDoctorService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 医生呼号业务实现类
 */
@Service
public class DoctorServiceImpl implements IDoctorService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 队列工厂
     */
    @Autowired
    private QueueCenterFactory queueCenterFactory;
    @Autowired
    private IQueueService queueService;

    public Map<String, Object> previous(String queueCode, String isVip, String currentTestno) throws Exception {

        /**
         * 上一个功能是在不清空队列的情况下才能实现的
         */

        Map<String, Object> result = new HashMap<String, Object>();

        //上一个体检者
        PerCheckinfo pre = null;

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        logger.debug("queueCenter is :" + queueCenter);

        if(Constants.IS_TRUE.equals(isVip)) {
            //是VIP队列
        } else {
            List<PerCheckinfo> perCheckinfoList = queueCenter.getPerCheckinfoList();
            if(null!=perCheckinfoList && perCheckinfoList.size()>0) {
                //队列不能为空
                for(int i=0;i<perCheckinfoList.size();i++) {
                    PerCheckinfo detectedOne = perCheckinfoList.get(i);
                    if(i>0) {
                        pre = detectedOne;
                    }
                    if(null!=detectedOne && !StringUtils.isBlank(detectedOne.getTestno())
                            && detectedOne.getTestno().equals(currentTestno)) {
                        break;
                    }
                } //for end
            }
        }
        if(null!=pre && !StringUtils.isBlank(pre.getTestno())) {
            result.put("status", Constants.RETURN_STATUS_SUCCESS);
            result.put("data", pre.getTestno());
        } else {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "暂时没有上一个体检者！");
        }

        return result;
    }

    public Map<String, Object> skip(String queueCode, String isVip, String currentTestno) throws Exception {

        /**
         * 要求跳过3次后，将此人清除出队列
         */

        Map<String, Object> result = new HashMap<String, Object>();

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        logger.debug("queueCenter is :" + queueCenter);

        //跳过的体检者
        PerCheckinfo skiped = null;

        if(Constants.IS_TRUE.equals(isVip)) {
            //是VIP队列
        } else {

            //找到跳过的体检者
            List<PerCheckinfo> perCheckinfoList = queueCenter.getPerCheckinfoList();
            if(null!=perCheckinfoList && perCheckinfoList.size()>0) {
                for(PerCheckinfo p : perCheckinfoList) {
                    if(null!=p && !StringUtils.isBlank(p.getTestno())
                            &&p.getTestno().equals(currentTestno)) {
                        skiped = p;
                        break;
                    }
                }
                if(null!=skiped) {
                    AtomicInteger skipCount = skiped.getSkip();
                    // >= 3次，自动跳过
                    if(skipCount.intValue()>=3) {
                        //将该体检者移出队列
                        queueCenter.remove(skiped);
                        logger.info("PerCheckinfo[testno={}] is removed from the queue[queueCode is {}, isVip is {}].", currentTestno, queueCode, isVip);
                    } else {

                        skiped.setSkip(skipCount.incrementAndGet());
                        //将跳过的用户加到队尾
                        queueCenter.remove(skiped);
                        queueCenter.produce(skiped);
                        logger.info("{} is removed from the queue[queueCode is {}, isVip is {}].", currentTestno, queueCode, isVip);
                    }
                    result.put("status", Constants.RETURN_STATUS_SUCCESS);
                    result.put("data", "成功跳过");
                } else {
                    result.put("status", Constants.RETURN_STATUS_FAILURE);
                    result.put("data", "跳过失败");
                }
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "跳过失败");
            }
        }

        return result;
    }

    public String wentTo(String queueCode, String isVip, String testno, String deviceNo) throws Exception {

        PerCheckinfo perCheckinfoPreparedToCheck = null;

        logger.debug("doctor went to check PerCheckinfo, queueCode is {}, isVip is{}.", queueCode, isVip);

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        logger.debug("queueCenter is :" + queueCenter);

        if(Constants.IS_TRUE.equals(isVip)) {
            //是VIP队列
        } else {

            for(PerCheckinfo p : queueCenter.getPerCheckinfoList()) {
                if(null!=p && !StringUtils.isBlank(p.getTestno()) && p.getTestno().equals(testno)) {
                    perCheckinfoPreparedToCheck = p;
                    //queueService.updatePerCheckinfoState(queueCode, p.getTestno(), Constants.CHECK_STATUS_ING);
                    break;
                } else if(null!=p && !StringUtils.isBlank(p.getTestno()) && !Constants.CHECK_STATUS_ING.equals(p.getState())
                        && !Constants.CHECK_STATUS_DONE.equals(p.getState())) {
                    //1. 获取队列中第一个体检状态不是"体检中"和"已完成"的
                    //&& 2. testno如果与p.getTestno()相同，
                    perCheckinfoPreparedToCheck = p;
                    queueService.updatePerCheckinfoState(queueCode, p.getTestno(), Constants.CHECK_STATUS_ING);
                    break;
                }
            }
            //perCheckinfoPreparedToCheck = queueCenter.peek();
        }

        return null!=perCheckinfoPreparedToCheck?perCheckinfoPreparedToCheck.getTestno():"";
    }

    public boolean complete(String queueCode, String isVip, String testno) throws Exception {

        /**
         * 要求，完成操作，不能将此人清除出队列。
         * 在检查完N个人之后，清队列，清除（N-1）个，目的是为了“上一个”功能可用
         */

        boolean removeFlage = false;

        PerCheckinfo perCheckinfoComplete = new PerCheckinfo();
        perCheckinfoComplete.setTestno(testno);

        logger.debug("doctor went to check PerCheckinfo, queueCode is {}, isVip is{}.", queueCode, isVip);

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        logger.debug("queueCenter is :" + queueCenter);

        if(Constants.IS_TRUE.equals(isVip)) {
            //是VIP队列
        } else {
            removeFlage = queueCenter.remove(perCheckinfoComplete);
        }

        return removeFlage;
    }

}
