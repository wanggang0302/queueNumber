package com.jfsoft.checker.service.impl;

import com.jfsoft.checker.service.ICheckerService;
import com.jfsoft.model.SysQueue;
import com.jfsoft.queue.core.QueueCenter;
import com.jfsoft.queue.factory.QueueCenterFactory;
import com.jfsoft.queue.service.IQueueService;
import com.jfsoft.sysqueue.service.ISysQueueService;
import com.jfsoft.vo.PerCheckinfo;
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
 * 体检者业务接口实现类
 */
@Service
public class CheckerServiceImpl implements ICheckerService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QueueCenterFactory queueCenterFactory;
    @Autowired
    private IQueueService queueService;
    @Autowired
    private ISysQueueService sysQueueService;

    public synchronized List<Map<String, Object>> getCheckerQueueInfo(String testno) throws Exception {

        List<Map<String, Object>> queueList = new ArrayList<Map<String, Object>>();

        //查询所有的队列（科室）
        List<SysQueue> sysQueueList = sysQueueService.findAll();
        for(SysQueue q : sysQueueList) {

            //单个队列的体检信息
            Map<String, Object> map = new HashMap<String, Object>();

            String queueCode = q.getCode().toString();
            //检查一个人需要的时间(单位:分钟)
            Float queueTime = null!=q.getQueuetime()?q.getQueuetime():0;
            //队列名称
            String queueName = q.getName();
            //体检状态
            String state = "";
            //需要等待的人数
            int waitNumber = 0;
            //需要等待的时间
            float waitTime = 0;

            //获得队列
            QueueCenter queueCenter = queueCenterFactory.obtain(queueCode);

            if(null!=queueCenter) {
                //VIP队列人数
                List<PerCheckinfo> vipPerCheckinfoList = queueCenter.getVipPerCheckinfoList();
                for (PerCheckinfo p : vipPerCheckinfoList) {
                    waitNumber++;
                    if (null != p && !StringUtils.isBlank(p.getTestno()) && p.getTestno().equals(testno)) {
                        //获得体检状态
                        state = p.getState();
                        break;
                    }
                }
                //普通队列人数
                List<PerCheckinfo> perCheckinfoList = queueCenter.getPerCheckinfoList();
                if (StringUtils.isBlank(state)) {
                    //如果体检状态为空，则继续查询普通队列
                    for (PerCheckinfo p : perCheckinfoList) {
                        waitNumber++;
                        if (null != p && !StringUtils.isBlank(p.getTestno()) && p.getTestno().equals(testno)) {
                            //获得体检状态
                            state = p.getState();
                            break;
                        }
                    }
                }
            }
            if(StringUtils.isBlank(state)) {
                //查询文件中的记录
                state = "";
            }
            waitTime = waitNumber * queueTime;

            map.put("queueName", queueName);
            map.put("state", state);
            map.put("waitCount", waitNumber);
            map.put("waitTime", waitTime);

            queueList.add(map);
        }

        return queueList;
    }

}
