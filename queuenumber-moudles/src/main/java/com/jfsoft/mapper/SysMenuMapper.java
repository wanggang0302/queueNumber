/**
 * SysMenuMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-09-06 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /**
     * 根据角色查询菜单
     */
    List<SysMenu> findMenuByRole(Map<String, Object> param);
    
}
