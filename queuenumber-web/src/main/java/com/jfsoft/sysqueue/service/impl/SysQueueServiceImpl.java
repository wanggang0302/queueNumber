package com.jfsoft.sysqueue.service.impl;

import com.jfsoft.mapper.SysQueueMapper;
import com.jfsoft.model.SysQueue;
import com.jfsoft.sysqueue.service.ISysQueueService;
import com.jfsoft.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统队列业务接口实现类
 */
@Service
public class SysQueueServiceImpl implements ISysQueueService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysQueueMapper sysQueueMapper;

    public List<SysQueue> findPage(String currentPage, String pageSize, String name) throws Exception {

        //当前页码
        int currentPageInt = Integer.parseInt(currentPage);
        //每页显示的条数
        int pageSizeInt = Integer.parseInt(pageSize);
        //当前页开始的条数
        int pageStart = currentPageInt * pageSizeInt + 1;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("pageSize", pageSizeInt);
        params.put("pageStart", pageStart);

        List<SysQueue> sysQueueList = sysQueueMapper.findPage(params);

        return sysQueueList;
    }

    public int findPageCount(String name) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);

        int sysQueueCount = sysQueueMapper.findPageCount(params);

        return sysQueueCount;
    }

    public Map<String, Object> save(SysQueue sysQueue) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();

        //队列名称
        String name = sysQueue.getName();
        SysQueue sysQueueExist = getByName(name);
        if(null!=sysQueueExist) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "已经存在该用户，不允许重复添加！");
        } else {
            int maxCode = getMaxCode();
            sysQueue.setCode(maxCode + 1);
            sysQueue.setCreateTime(new Date());
            int count = sysQueueMapper.insertSelective(sysQueue);
            if(count>0) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "保存队列成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "保存队列失败！");
            }
        }

        return result;
    }

    public Map<String, Object> update(SysQueue sysQueue) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();

        //用户账号
        String name = sysQueue.getName();
        SysQueue sysQueueExist = getByName(name);
        if(null!=sysQueueExist) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "已经存在该队列，不允许重复添加！");
        } else {
            int count = sysQueueMapper.updateByPrimaryKeySelective(sysQueue);
            if(count>0) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "修改队列成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "修改队列失败！");
            }
        }

        return result;
    }

    public boolean delete(String id) throws Exception {
        int count = sysQueueMapper.deleteByPrimaryKey(id);
        if(count>0) {
            return true;
        }
        return false;
    }

    public SysQueue getByName(String name) throws Exception {

        SysQueue sysQueue = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);

        //查询数据库
        sysQueue = sysQueueMapper.findByName(params);

        return sysQueue;
    }

    public int getMaxCode() throws Exception {

        int maxCode = sysQueueMapper.getMaxCode();

        return maxCode;
    }

    public List<SysQueue> findAll() throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();

        List<SysQueue> sysQueueList = sysQueueMapper.findAll(params);

        return sysQueueList;
    }

}
