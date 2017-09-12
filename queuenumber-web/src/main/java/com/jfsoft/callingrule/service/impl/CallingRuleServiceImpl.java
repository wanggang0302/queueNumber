package com.jfsoft.callingrule.service.impl;

import com.jfsoft.callingrule.service.ICallingRuleService;
import com.jfsoft.mapper.CallingRuleMapper;
import com.jfsoft.model.CallingRule;
import com.jfsoft.model.SysUser;
import com.jfsoft.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 呼号规则设置业务接口实现类
 */
@Service
public class CallingRuleServiceImpl implements ICallingRuleService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CallingRuleMapper callingRuleMapper;

    public List<CallingRule> findPage(String pageStart, String pageSize, String beginTime, String endTime, String sexPriority) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        if(!StringUtils.isBlank(beginTime)) {
            params.put("searchBeginTime", beginTime + Constants.SEARCH_TIME_BEGIN);
        }
        if(!StringUtils.isBlank(endTime)) {
            params.put("searchEndTime", endTime + Constants.SEARCH_TIME_END);
        }
        if(!StringUtils.isBlank(sexPriority)) {
            params.put("sexPriority", sexPriority);
        }
        params.put("pageSize", pageSize);
        params.put("pageStart", pageStart);

        List<CallingRule> sysUserList = callingRuleMapper.findPage(params);

        return sysUserList;
    }

    public int findPageCount(String beginTime, String endTime, String sexPriority) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        if(!StringUtils.isBlank(beginTime)) {
            params.put("searchBeginTime", beginTime + Constants.SEARCH_TIME_BEGIN);
        }
        if(!StringUtils.isBlank(endTime)) {
            params.put("searchEndTime", endTime + Constants.SEARCH_TIME_END);
        }
        if(!StringUtils.isBlank(sexPriority)) {
            params.put("sexPriority", sexPriority);
        }

        int userCount = callingRuleMapper.findPageCount(params);

        return userCount;
    }

}
