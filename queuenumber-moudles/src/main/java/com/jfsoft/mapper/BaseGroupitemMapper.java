/**
 * BaseGroupitemMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.BaseGroupitem;

import java.util.List;
import java.util.Map;

public interface BaseGroupitemMapper {
    
    int deleteByPrimaryKey(String id);

    int insert(BaseGroupitem record);

    int insertSelective(BaseGroupitem record);

    BaseGroupitem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseGroupitem record);

    int updateByPrimaryKey(BaseGroupitem record);

    /**
     * 分页查询组合项目
     */
    List<BaseGroupitem> findPage(Map<String, Object> params);

    /**
     * 查询组合项目总数
     */
    int findPageCount(Map<String, Object> params);

    /**
     * 批量插入
     */
    int insertBatch(List<BaseGroupitem> list);

}
