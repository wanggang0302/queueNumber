package com.jfsoft.callingrule.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.base.BaseController;
import com.jfsoft.callingrule.service.ICallingRuleService;
import com.jfsoft.model.CallingRule;
import com.jfsoft.model.SysQueue;
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
 * 呼号规则管理
 */
@Controller
@RequestMapping("/callingRule")
public class CallingRuleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICallingRuleService callingRuleService;

    /**
     * 跳转到呼号器角色管理页面
     * @return
     */
    @RequestMapping(value="/toList", method= RequestMethod.GET)
    public String toList(ModelMap model) {

        return "/callingrule/list";
    }

    /**
     * 查询呼号器角色列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(String iDisplayStart, String iDisplayLength, String draw,
                       String name, String searchBeginTime, String searchEndTime, String sexPriority) {

        Map<String, Object> result = new HashMap<String, Object>();

        List<CallingRule> callingRuleList = null;
        int pageCount = 0;

        try {
            callingRuleList = callingRuleService.findPage(iDisplayStart, iDisplayLength, searchBeginTime, searchEndTime, sexPriority);
            pageCount = callingRuleService.findPageCount(searchBeginTime, searchEndTime, sexPriority);

            result.put("draw", draw);
            result.put("data", callingRuleList);
            //表的总记录数
            result.put("recordsTotal", pageCount);
            //条件过滤后记录数
            result.put("recordsFiltered", pageCount);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            e.printStackTrace();
        }

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(CallingRule.class,
                "id", "code", "sexpriority", "isvip", "createtime");

        String resultJson = JSON.toJSONString(result, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return resultJson;
    }

}
