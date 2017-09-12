/**
 * CallingRuleMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-09-12 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.CallingRule;

import java.util.List;
import java.util.Map;

public interface CallingRuleMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(CallingRule record);

    int insertSelective(CallingRule record);

    CallingRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CallingRule record);

    int updateByPrimaryKey(CallingRule record);

    /**
     * 分页查询用户信息
     */
    List<CallingRule> findPage(Map<String, Object> params);

    /**
     * 查询用户总数
     */
    int findPageCount(Map<String, Object> params);
    
}