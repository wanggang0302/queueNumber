package com.jfsoft.advertisement.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.base.BaseController;
import com.jfsoft.advertisement.service.IAdvertisementService;
import com.jfsoft.model.Advertisement;
import com.jfsoft.utils.Constants;
import com.jfsoft.vo.PerCheckinfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 广告管理
 */
@Controller
@RequestMapping("/ad")
public class AdvertisementController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IAdvertisementService advertisementService;

    /**
     * 体检者等候区，查询广告
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(String currentPage, String pageSize, String queueCode) {

        Map<String, Object> result = new HashMap<String, Object>();

        List<Advertisement> advertisementList = null;
        int pageCount = 0;

        try {
            advertisementList = advertisementService.findPage(currentPage, pageSize, queueCode);
            pageCount = advertisementService.findPageCount(queueCode);

            result.put("status", Constants.RETURN_STATUS_SUCCESS);
            result.put("data", advertisementList);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Advertisement.class, "id", "code", "queueName", "isuse", "createtime");

        return JSON.toJSONString(result, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 新增广告信息
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpSession session, Advertisement advertisement) {

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String userCode = getUserCodeFromSession(session);
            advertisement.setCreaterid(!StringUtils.isBlank(userCode)?Integer.parseInt(userCode):0);
            result = advertisementService.save(advertisement);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "添加广告失败！");
            e.printStackTrace();
        }

        String resultJson = JSON.toJSONString(result);

        return resultJson;
    }

    /**
     * 修改广告信息
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpSession session, Advertisement advertisement) {

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = advertisementService.update(advertisement);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "修改广告失败！");
            e.printStackTrace();
        }

        String resultJson = JSON.toJSONString(result);

        return resultJson;
    }

    /**
     * 删除广告信息
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpSession session, String id) {

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            boolean flage = advertisementService.delete(id);
            if(flage) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "删除广告成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "删除广告失败！");
            }
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "删除广告失败！");
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        String resultJson = JSON.toJSONString(result);

        return resultJson;
    }

}
