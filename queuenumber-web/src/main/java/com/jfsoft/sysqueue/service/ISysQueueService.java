package com.jfsoft.sysqueue.service;

import com.jfsoft.model.SysQueue;

import java.util.List;
import java.util.Map;

/**
 * 系统队列业务接口
 */
public interface ISysQueueService {

    /**
     * 分页查询队列
     */
    public List<SysQueue> findPage(String pageStart, String pageSize, String name) throws Exception;

    /**
     * 查询全部队队列
     */
    public int findPageCount(String name) throws Exception;

    /**
     * 保存队列
     */
    public Map<String, Object> save(SysQueue sysQueue) throws Exception;

    /**
     * 修改队列
     */
    public Map<String, Object> update(SysQueue sysQueue) throws Exception;

    /**
     * 删除队列
     */
    public boolean delete(String id) throws Exception;

    /**
     * 根据队列名称查询队列信息
     */
    public SysQueue getByName(String name) throws Exception;

    /**
     * 查询队列最大编号
     */
    public int getMaxCode() throws Exception;

    /**
     * 查询所有队列
     */
    public List<SysQueue> findAll() throws Exception;

}
