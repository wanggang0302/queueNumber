package com.jfsoft.sysqueue.service.impl;

import com.jfsoft.mapper.SysQueueMapper;
import com.jfsoft.model.SysQueue;
import com.jfsoft.sysqueue.service.ISysQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统队列业务接口实现类
 */
@Service
public class SysQueueServiceImpl implements ISysQueueService {

    @Autowired
    private SysQueueMapper sysQueueMapper;

    public List<SysQueue> findAll() throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        List<SysQueue> sysQueueList = sysQueueMapper.findAll(params);

        return sysQueueList;
    }

}
