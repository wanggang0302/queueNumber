package com.jfsoft.callingrule.service;

import com.jfsoft.model.CallingRule;

import java.util.List;

/**
 * 呼号角色设置业务接口
 */
public interface ICallingRuleService {

    /**
     * 分页查询呼号器角色
     */
    public List<CallingRule> findPage(String pageStart, String pageSize, String beginTime, String endTime, String sexPriority) throws Exception;

    /**
     * 查询呼号器角色总数
     */
    public int findPageCount(String beginTime, String endTime, String sexPriority) throws Exception;

}
