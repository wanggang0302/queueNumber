package com.jfsoft.basegroupitem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.base.BaseController;
import com.jfsoft.basegroupitem.service.IBaseGroupitemService;
import com.jfsoft.model.BaseGroupitem;
import com.jfsoft.model.SysUser;
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
 * 组合项目管理
 */
@Controller
public class BaseGroupitemController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IBaseGroupitemService baseGroupitemService;

    /**
     * 查询组合项目列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(String currentPage, String pageSize, String name) {

        Map<String, Object> result = new HashMap<String, Object>();

        List<BaseGroupitem> baseGroupitemList = null;
        int pageCount = 0;

        try {
            baseGroupitemList = baseGroupitemService.findPage(currentPage, pageSize, name);
            pageCount = baseGroupitemService.findPageCount(name);

            result.put("status", Constants.RETURN_STATUS_SUCCESS);
            result.put("data", baseGroupitemList);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            e.printStackTrace();
        }

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(BaseGroupitem.class,
                "id", "code", "name", "deptcode", "deptname", "isuse", "synctime");

        String resultJson = JSON.toJSONString(result, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return resultJson;
    }

    /**
     * 同步组合项目信息
     */
    @ResponseBody
    @RequestMapping(value = "/sync", method = RequestMethod.POST)
    public String sync() {

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            boolean flage = baseGroupitemService.sync();
            if(flage) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "同步成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "同步失败！");
            }
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "同步失败！");
            e.printStackTrace();
        }

        String resultJson = JSON.toJSONString(result);

        return resultJson;
    }

}
