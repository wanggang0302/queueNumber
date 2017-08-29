package com.jfsoft.nurse.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.jfsoft.queue.entity.PerCheckinfo;
import com.jfsoft.queue.service.IQueueService;
import com.jfsoft.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 护士
 */
@Controller
@RequestMapping("/nurse")
public class NurseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IQueueService queueService;

    /**
     * 查询某个队列中的所有体检者，按序输出
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String findPerCheckinfoListOfQueue(String queueCode) {

        logger.debug("controller findPerCheckinfoListOfQueue.");

        Map<String, Object> result = new HashMap<String, Object>();
        try {

            List<PerCheckinfo> perCheckinfoListOfQueue = queueService.findPerCheckinfoListOfQueue(queueCode);
            logger.debug("controller findPerCheckinfoListOfQueue. list size is :" + perCheckinfoListOfQueue.size());
            result.put("data", perCheckinfoListOfQueue);
            result.put("status", Constants.RETURN_STATUS_SUCCESS);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            e.printStackTrace();
        }

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(PerCheckinfo.class, "testno", "name", "sex");

        return JSON.toJSONString(result, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 队列中增加一个体检者
     * @return
     */
    @RequestMapping("/put")
    public String putChecker(String queueCode, PerCheckinfo perCheckinfo) {

        queueService.addPerCheckinfo(queueCode, perCheckinfo);

        //添加成功，返回成功状态
        return "/doctor/queue/list";
    }

}
