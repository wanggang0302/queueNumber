package com.jfsoft.sysqueue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.base.BaseController;
import com.jfsoft.model.SysQueue;
import com.jfsoft.model.SysUser;
import com.jfsoft.sysqueue.service.ISysQueueService;
import com.jfsoft.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统队列管理
 */
@Controller
@RequestMapping("/sysqueue")
public class SysQueueController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISysQueueService sysQueueService;

    /**
     * 查询队列列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(ModelMap model, String currentPage, String pageSize, String name) {

        Map<String, Object> result = new HashMap<String, Object>();

        List<SysQueue> sysQueueList = null;
        int pageCount = 0;

        try {
            sysQueueList = sysQueueService.findPage(currentPage, pageSize, name);
            pageCount = sysQueueService.findPageCount(name);

            result.put("status", Constants.RETURN_STATUS_SUCCESS);
            result.put("data", sysQueueList);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(SysQueue.class,
                "id", "code", "name", "queuelocation", "isneedqueueup", "isenable", "queuetime", "isshowad", "isperterm");

        String resultJson = JSON.toJSONString(result, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return resultJson;
    }

    /**
     * 新增队列信息
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpSession session, SysQueue sysQueue) {

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String userCode = getUserCodeFromSession(session);
            sysQueue.setCreateId(userCode);
            result = sysQueueService.save(sysQueue);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "保存失败！");
            e.printStackTrace();
        }

        String resultJson = JSON.toJSONString(result);

        return resultJson;
    }

    /**
     * 修改队列信息
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpSession session, SysQueue sysQueue) {

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = sysQueueService.update(sysQueue);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "修改队列信息失败！");
            e.printStackTrace();
        }

        String resultJson = JSON.toJSONString(result);

        return resultJson;
    }

    /**
     * 删除队列信息
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpSession session, String id) {

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            boolean flage = sysQueueService.delete(id);
            if(flage) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "删除队列成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "删除队列失败！");
            }
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "删除队列失败！");
            e.printStackTrace();
        }

        String resultJson = JSON.toJSONString(result);

        return resultJson;
    }

}
