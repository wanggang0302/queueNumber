/**
 * SysQueueMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.SysQueue;

import java.util.List;
import java.util.Map;

public interface SysQueueMapper {
    
    int deleteByPrimaryKey(String id);

    int insert(SysQueue record);

    int insertSelective(SysQueue record);

    SysQueue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysQueue record);

    int updateByPrimaryKey(SysQueue record);

    /**
     * 查询所有的队列
     */
    List<SysQueue> findAll(Map<String, Object> params);
    
}