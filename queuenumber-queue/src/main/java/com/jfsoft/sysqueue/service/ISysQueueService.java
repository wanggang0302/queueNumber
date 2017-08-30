package com.jfsoft.sysqueue.service;

import com.jfsoft.model.SysQueue;

import java.util.List;

/**
 * 系统队列业务接口
 */
public interface ISysQueueService {

    /**
     * 查询所有的系统队列
     */
    public List<SysQueue> findAll() throws Exception;

}
