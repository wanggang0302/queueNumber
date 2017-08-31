package com.jfsoft.queue.service.impl;

import com.jfsoft.model.SysQueue;
import com.jfsoft.queue.core.QueueCenter;
import com.jfsoft.vo.PerCheckinfo;
import com.jfsoft.queue.factory.QueueCenterFactory;
import com.jfsoft.queue.service.IQueueService;
import com.jfsoft.sysqueue.service.ISysQueueService;
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
    @Autowired
    private ISysQueueService sysQueueService;

    public List<SysQueue> findListOfQueue() throws Exception {

        //查询所有的队列（科室）
        List<SysQueue> sysQueueList = sysQueueService.findAll();
        for(SysQueue q : sysQueueList) {
            String queueCode = q.getCode().toString();
            List<PerCheckinfo> perList = getPerListOfQueue(queueCode);
            q.setPerCheckinfoList(perList);
        }

        return sysQueueList;
    }

    public List<PerCheckinfo> findPerCheckinfoListOfQueue(String queueCode, String isVip) {

        logger.debug("load queue for {}, isVip is {}.", queueCode, isVip);

        List<PerCheckinfo> perCheckinfoList = new ArrayList<PerCheckinfo>();

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        logger.debug("queueCenter is :" + queueCenter);

        if(null!=queueCenter) {
            //获得队列中的体检者
            if(Constants.IS_TRUE.equals(isVip)) {
                //加载VIP队列
                perCheckinfoList = queueCenter.getVipPerCheckinfoList();
            } else {
                //加载普通队列
                perCheckinfoList = queueCenter.getPerCheckinfoList();
            }
            if(null!=perCheckinfoList && perCheckinfoList.size()>0) {
                PerCheckinfo p = perCheckinfoList.get(0);
                if (null != p && !StringUtils.isBlank(p.getTestno())) {
                    if (StringUtils.isBlank(p.getState())) {
                        p.setState(Constants.CHECK_STATUS_PREPARE);
                    }
                }
            }
        }
        logger.debug("perCheckinfoList size is :" + perCheckinfoList.size());

        return perCheckinfoList;
    }

    public Map<String, Object> addPerCheckinfo(String queueCode, PerCheckinfo perCheckinfo) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            //数据格式验证
            if(!validPerCheckinfo(perCheckinfo)) {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "体检号不能为空！");
                return result;
            }

            //是否VIP
            String isVip = perCheckinfo.getIsVip();

            //判断体检者是否在普通队列和VIP队列中存在（0:都不存在，1:在VIP队列中，2:在普通队列中）
            String isExist = "0";

            //判断是否在此队列中排队
            QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
            if(null!=queueCenter) {
                //判断队列是否存在此体检者
                List<PerCheckinfo> vipList = queueCenter.getVipPerCheckinfoList();
                for(PerCheckinfo p : vipList) {
                    if(!StringUtils.isBlank(p.getTestno()) && p.getTestno().equals(perCheckinfo.getTestno())) {
                        isExist = "1";
                        break;
                    }
                }
                List<PerCheckinfo> perCheckinfoList = queueCenter.getPerCheckinfoList();
                for(PerCheckinfo p : perCheckinfoList) {
                    if(!StringUtils.isBlank(p.getTestno()) && p.getTestno().equals(perCheckinfo.getTestno())) {
                        isExist = "2";
                        break;
                    }
                }
            } // for end

            if(Constants.IS_FALSE.equals(isExist)) {
                //将体检者加入队列
                queueCenterFactory.create(queueCode, perCheckinfo);
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "添加成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                if("1".equals(isExist)) {
                    result.put("data", "体检者在VIP队列中，不能重复加入！");
                } else if("2".equals(isExist)) {
                    result.put("data", "体检者在普通队列中，不能重复加入！");
                }
            }
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "体检者加入队列失败！");
            logger.error(e.getMessage(), e);
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

    public synchronized boolean moveVertical(String queueCode, String isVip, String testnoUp, String testnoDown) throws Exception {

        //垂直移动

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        List<PerCheckinfo> oldList = null;
        if(Constants.IS_TRUE.equals(isVip)) {
            oldList = queueCenter.getVipPerCheckinfoList();
        } else {
            oldList = queueCenter.getPerCheckinfoList();
        }

        if(null!=oldList && oldList.size()>0) {

            //调整队列顺序
            List<PerCheckinfo> newList = moveVertical(oldList, testnoUp, testnoDown);

            if (null != newList) {
                //更新队列
                return queueCenter.update(newList);
            }
        } else {
            logger.debug("Operation failure! The queue with code[{}] not found!", queueCode);
        }

        return false;
    }

    public synchronized Map<String, Object> moveHorizontal(String queueCode, String testnoFromQueue, String testnoFromVipQueue) throws Exception {

        //水平移动

        Map<String, Object> result = new HashMap<String, Object>();

        //queueCode不能为空
        if(StringUtils.isBlank(queueCode)) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "操作失败！");
            return result;
        }

        //两个体检号不能同时为空
        if(StringUtils.isBlank(testnoFromQueue) && StringUtils.isBlank(testnoFromVipQueue)) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "操作失败！");
            return result;
        }

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);

        //从普通队列中移除testnoFromQueue
        PerCheckinfo removedFromQueue = null;
        if(!StringUtils.isBlank(testnoFromQueue)) {
            removedFromQueue = new PerCheckinfo();
            removedFromQueue.setTestno(testnoFromQueue);
            removedFromQueue.setIsVip(Constants.IS_FALSE);
            queueCenter.remove(removedFromQueue);
        }

        //从VIP队列中移除testnoFromVipQueue
        PerCheckinfo removedFromVipQueue = null;
        if(!StringUtils.isBlank(testnoFromVipQueue)) {
            removedFromVipQueue = new PerCheckinfo();
            removedFromVipQueue.setTestno(testnoFromVipQueue);
            removedFromVipQueue.setIsVip(Constants.IS_TRUE);
            queueCenter.remove(removedFromVipQueue);
        }

        //将testnoFromVipQueue添加到普通队列
        if(null!=removedFromVipQueue) {
            removedFromVipQueue.setIsVip(Constants.IS_FALSE);
            queueCenter.produce(removedFromVipQueue);
        }

        //将testnoFromQueue添加到VIP队列
        if(null!=removedFromQueue) {
            removedFromQueue.setIsVip(Constants.IS_TRUE);
            queueCenter.produce(removedFromQueue);
        }

        result.put("status", Constants.RETURN_STATUS_SUCCESS);
        result.put("data", "跨队列调整成功！");

        return result;
    }

    /**
     * 调整list顺序（上下调整）
     */
    private List<PerCheckinfo> moveVertical(List<PerCheckinfo> oldList, String testnoUp, String testnoDown) throws Exception {

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

    /**
     * 根据队列编号获取队列中的体检者
     */
    private List<PerCheckinfo> getPerListOfQueue(String queueCode) throws Exception {

        List<PerCheckinfo> perCheckinfoList = new ArrayList<PerCheckinfo>();

        //获得队列
        QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);
        logger.debug("queueCenter is :" + queueCenter);

        if(null!=queueCenter) {

            //优先查询VIP队列
            List<PerCheckinfo> vipList = queueCenter.getVipPerCheckinfoList();
            if(null!=vipList && vipList.size()>0) {
                perCheckinfoList.addAll(vipList);
            }

            //获得队列中的体检者
            List<PerCheckinfo> list = queueCenter.getPerCheckinfoList();
            if(null!=list && list.size()>0) {
                perCheckinfoList.addAll(list);
            }
        }

        return perCheckinfoList;
    }

    /**
     * 体检者信息验证
     */
    private boolean validPerCheckinfo(PerCheckinfo perCheckinfo) throws Exception {

        if(null!=perCheckinfo && !StringUtils.isBlank(perCheckinfo.getTestno())) {
            return true;
        }

        return false;
    }

}
