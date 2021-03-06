/**
 * SysUserMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {

    /**
     * 根据用户编号删除用户
     */
    int deleteByPrimaryKey(String id);

    /**
     * 批量删除用户
     */
    int deleteBatch(Map<String, Object> params);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    /**
     * 根据用户编号查询唯一用户
     */
    SysUser selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据业务唯一主键（用户账号）查询用户信息
     */
    SysUser findByUsername(Map<String, Object> params);

    /**
     * 分页查询用户信息
     */
    List<SysUser> findPage(Map<String, Object> params);

    /**
     * 查询用户总数
     */
    int findPageCount(Map<String, Object> params);

    /**
     * 查询最大用户编号
     */
    int getMaxCode();

}