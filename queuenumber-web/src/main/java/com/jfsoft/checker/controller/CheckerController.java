package com.jfsoft.checker.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.base.BaseController;
import com.jfsoft.checker.service.ICheckerService;
import com.jfsoft.model.SysQueue;
import com.jfsoft.vo.PerCheckinfo;
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
 * 体检者
 */
@Controller
@RequestMapping("/checker")
public class CheckerController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICheckerService checkerService;
    @Autowired
    private IQueueService queueService;

    /**
     * 体检者等候区
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String findListOfQueue() {

        Map<String, Object> result = new HashMap<String, Object>();

        try {

            List<SysQueue> queueList = queueService.findListOfQueue();

            result.put("data", queueList);
            result.put("status", Constants.RETURN_STATUS_SUCCESS);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            e.printStackTrace();
        }

        SimplePropertyPreFilter sysQueueFilter = new SimplePropertyPreFilter(SysQueue.class, "code", "name", "perCheckinfoList");
        SimplePropertyPreFilter perCheckinfoFilter = new SimplePropertyPreFilter(PerCheckinfo.class, "testno", "name", "sex", "state");

        SerializeFilter[] filters = new SerializeFilter[2];
        filters[0] = sysQueueFilter;
        filters[1] = perCheckinfoFilter;

        String jsonStr = JSON.toJSONString(result, filters,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return jsonStr;
    }

    /**
     * 体检者综合信息查询
     */
    @ResponseBody
    @RequestMapping(value = "/whole", method = RequestMethod.POST)
    public String whole(String testno) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Map<String, Object>> list = checkerService.getCheckerQueueInfo(testno);
            result.put("data", list);
            result.put("status", Constants.RETURN_STATUS_SUCCESS);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            logger.error(e.getMessage(), e);
        }

        String jsonStr = JSON.toJSONString(result,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return jsonStr;
    }

}
