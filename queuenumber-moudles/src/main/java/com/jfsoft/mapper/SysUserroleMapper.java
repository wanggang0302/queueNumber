/**
 * SysUserroleMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-09-06 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.SysUserrole;

public interface SysUserroleMapper {
    
    int insert(SysUserrole record);

    int insertSelective(SysUserrole record);
    
}
