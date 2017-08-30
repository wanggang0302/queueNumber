package com.jfsoft.advertisement.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.base.BaseController;
import com.jfsoft.vo.PerCheckinfo;
import com.jfsoft.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 广告
 */
@Controller
@RequestMapping("/ad")
public class AdvertisementController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 体检者等候区，查询广告
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(String queueCode) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {



            result.put("status", Constants.RETURN_STATUS_SUCCESS);
            result.put("data", "");
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

}
