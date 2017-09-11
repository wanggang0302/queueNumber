package com.jfsoft.user.service;

import com.jfsoft.model.SysQueue;
import com.jfsoft.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 系统用户业务接口
 */
public interface ISysUserService {

    /**
     * 分页查询
     */
    public List<SysUser> findPage(String currentPage, String pageSize, String name, String beginTime, String endTime, String queueCode) throws Exception;

    /**
     * 根据条件查询总条数
     */
    public int findPageCount(String name, String beginTime, String endTime, String queueCode) throws Exception;

    /**
     * 新增用户
     */
    public Map<String, Object> save(SysUser sysUser) throws Exception;

    /**
     * 修改用户
     */
    public Map<String, Object> update(SysUser sysUser) throws Exception;

    /**
     * 删除用户
     */
    public boolean delete(String id) throws Exception;

    /**
     * 根据业务唯一主键（用户账号）查询用户信息
     */
    public SysUser getByUsername(String username) throws Exception;

    /**
     * 验证用户名、密码
     */
    public SysUser findForAuthentication(String username, String password) throws Exception;

    /**
     * 查询最大用户编号
     */
    public int getMaxCode() throws Exception;

    /**
     * 查询全部科室
     */
    public List<SysQueue> getAllSysQueue() throws Exception;

    /**
     * 根据用户编号查询唯一用户
     */
    public SysUser getByCode(String code) throws Exception;

}
