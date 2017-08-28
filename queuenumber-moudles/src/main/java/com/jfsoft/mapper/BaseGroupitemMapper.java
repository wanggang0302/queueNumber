/**
 * BaseGroupitemMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.BaseGroupitem;

public interface BaseGroupitemMapper {
    
    int deleteByPrimaryKey(String id);

    int insert(BaseGroupitem record);

    int insertSelective(BaseGroupitem record);

    BaseGroupitem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseGroupitem record);

    int updateByPrimaryKey(BaseGroupitem record);
    
}