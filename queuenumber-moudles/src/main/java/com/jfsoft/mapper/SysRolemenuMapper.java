/**
 * SysRolemenuMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-09-06 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.SysRolemenu;

public interface SysRolemenuMapper {
    
    int insert(SysRolemenu record);

    int insertSelective(SysRolemenu record);
    
}
