package com.jfsoft.queue.controller;

import com.jfsoft.queue.core.QueueCenter;
import com.jfsoft.queue.entity.PerCheckinfo;
import com.jfsoft.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.BlockingQueue;

/**
 * 内存队列
 * wanggang
 * 2017年8月28日
 */
@Controller
public class QueueController {

    /**
     *
     */
    private QueueCenter queueCenter;

    /**
     * 队列中增加一个体检者
     * @return
     */
    @ResponseBody
    @RequestMapping("/put")
    public String putChecker(PerCheckinfo perCheckinfo) {

        //体检者队列
        BlockingQueue<PerCheckinfo> perCheckinfos = queueCenter.getPerCheckinfos();

        //添加成功，返回成功状态
        return Constants.RETURN_STATUS_SUCCESS;
    }

}
