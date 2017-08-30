package com.jfsoft.doctor.controller;

import com.alibaba.fastjson.JSON;
import com.base.BaseController;
import com.jfsoft.doctor.service.IDoctorService;
import com.jfsoft.queue.entity.PerCheckinfo;
import com.jfsoft.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生呼号Controller
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDoctorService doctorService;

    /**
     * 上一个
     * @return
     */
    @RequestMapping("/pre")
    public String previous(String queueCode, String isVip, String testno) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            doctorService.previous(queueCode, isVip, testno);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            e.printStackTrace();
        }

        return JSON.toJSONString(result);
    }

    /**
     * 跳过
     * @return
     */
    @RequestMapping("/skip")
    public String skip(String queueCode, String isVip, String testno) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {

            result = doctorService.skip(queueCode, isVip, testno);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            e.printStackTrace();
        }

        return JSON.toJSONString(result);
    }

    /**
     * 呼叫
     * @return
     */
    @RequestMapping("/call")
    public String wentTo(String queueCode, String isVip, String testno, String deviceNo) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {

            String callTestno = doctorService.wentTo(queueCode, isVip, testno, deviceNo);
            result.put("status", Constants.RETURN_STATUS_SUCCESS);
            result.put("data", callTestno);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            e.printStackTrace();
        }

        return JSON.toJSONString(result);
    }

    /**
     * 完成
     * @return
     */
    @RequestMapping("/done")
    public String done(String queueCode, String isVip, String testno) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            boolean removeFlage = doctorService.complete(queueCode, isVip, testno);
            result.put("status", Constants.RETURN_STATUS_SUCCESS);
            result.put("data", testno);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", testno);
            e.printStackTrace();
        }

        return JSON.toJSONString(result);
    }

}
