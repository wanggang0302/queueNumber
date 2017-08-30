package com.jfsoft.queue.controller;

import com.jfsoft.vo.PerCheckinfo;
import com.jfsoft.queue.service.IQueueService;
import com.jfsoft.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内存队列
 * wanggang
 * 2017年8月28日
 */
@Controller
public class QueueController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IQueueService queueService;

    /**
     * 查询某个队列中的所有体检者，按序输出
     */
    @RequestMapping("/find")
    public List<PerCheckinfo> findPerCheckinfoListOfQueue(String queueCode) {

        logger.debug("controller findPerCheckinfoListOfQueue.");

        List<PerCheckinfo> perCheckinfoListOfQueue = queueService.findPerCheckinfoListOfQueue(queueCode);
        logger.debug("controller findPerCheckinfoListOfQueue. list size is :" + perCheckinfoListOfQueue.size());

        return perCheckinfoListOfQueue;
    }

    /**
     * 队列中增加一个体检者
     * @return
     */
    @ResponseBody
    @RequestMapping("/put")
    public String putChecker(String queueCode, PerCheckinfo perCheckinfo) {

        queueService.addPerCheckinfo(queueCode, perCheckinfo);

        //添加成功，返回成功状态
        return Constants.RETURN_STATUS_SUCCESS;
    }

    /**
     * 调整队列顺序
     * @return
     */
    public String adjust(String queueCode) {

        return Constants.RETURN_STATUS_SUCCESS;
    }

}
