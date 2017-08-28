/**
 * CallingDeviceMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.CallingDevice;

public interface CallingDeviceMapper {
    
    int deleteByPrimaryKey(String id);

    int insert(CallingDevice record);

    int insertSelective(CallingDevice record);

    CallingDevice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CallingDevice record);

    int updateByPrimaryKey(CallingDevice record);
    
}