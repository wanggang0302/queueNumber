package com.jfsoft.nurse.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.base.BaseController;
import com.jfsoft.vo.PerCheckinfo;
import com.jfsoft.queue.service.IQueueService;
import com.jfsoft.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
public class NurseController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IQueueService queueService;

    /**
     * 队列页
     */
    @RequestMapping("/page")
    public String page() {

        return "/doctor/queue/list";
    }

    /**
     * 查询某个队列中的所有体检者，按序输出
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String findPerCheckinfoListOfQueue(String queueCode, String isVip) {

        logger.debug("controller findPerCheckinfoListOfQueue.");

        Map<String, Object> result = new HashMap<String, Object>();
        try {

            List<PerCheckinfo> perCheckinfoListOfQueue = queueService.findPerCheckinfoListOfQueue(queueCode, isVip);
            logger.debug("controller findPerCheckinfoListOfQueue. list size is :" + perCheckinfoListOfQueue.size());
            result.put("data", perCheckinfoListOfQueue);
            result.put("status", Constants.RETURN_STATUS_SUCCESS);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            e.printStackTrace();
        }

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(PerCheckinfo.class, "testno", "name", "sex", "state");

        return JSON.toJSONString(result, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 队列中增加一个体检者
     */
    @RequestMapping("/put")
    public String putChecker(ModelMap model, String queueCode, PerCheckinfo perCheckinfo) {

        Map<String, Object> result = queueService.addPerCheckinfo(queueCode, perCheckinfo);
        model.put("result", JSON.toJSONString(result));

        //添加成功，返回成功状态
        return "/doctor/queue/list";
    }

    /**
     * 从队列中删除体检者
     */
    @RequestMapping("/delete")
    public String deleteChecker(ModelMap model, String queueCode, PerCheckinfo perCheckinfo) {

        Map<String, Object> result = new HashMap<String, Object>();

        boolean removeState = queueService.deletePerCheckinfo(queueCode, perCheckinfo);
        if(removeState) {
            result.put("status", Constants.RETURN_STATUS_SUCCESS);
            result.put("data", "删除成功！");
        } else {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "删除失败！");
        }
        model.put("result", JSON.toJSONString(result));

        return "/doctor/queue/list";
    }

    /**
     * 调整队列顺序
     */
    @RequestMapping("/adjust")
    public String adjustQueue(ModelMap model, String queueCode, String isVip, String testnoUp, String testnoDown) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {

            boolean removeState = queueService.moveVertical(queueCode, isVip, testnoUp, testnoDown);

            if(removeState) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "调整顺序成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "调整顺序失败！");
            }
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "调整顺序失败！");
            logger.error("adjustQueue error! err msg is {}.", e.getMessage());
            e.printStackTrace();
        }

        model.put("result", JSON.toJSONString(result));

        return "/doctor/queue/list";
    }

    /**
     * 跨队列调整
     */
    @RequestMapping("/cross")
    public String crossQueue(ModelMap model, String queueCode, String testnoFromQueue, String testnoFromVipQueue) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {

            result = queueService.moveHorizontal(queueCode, testnoFromQueue, testnoFromVipQueue);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "跨队列调整失败！");
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        model.put("result", JSON.toJSONString(result));

        return "/doctor/queue/list";
    }

}
