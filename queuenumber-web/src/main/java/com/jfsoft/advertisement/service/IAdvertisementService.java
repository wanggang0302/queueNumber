package com.jfsoft.advertisement.service;

import com.jfsoft.model.Advertisement;

import java.util.List;
import java.util.Map;

/**
 * 广告管理业务接口
 */
public interface IAdvertisementService {

    /**
     * 分页查询广告
     */
    public List<Advertisement> findPage(String currentPage, String pageSize, String queueCode) throws Exception;

    /**
     * 查询广告总数
     */
    public int findPageCount(String queueCode) throws Exception;

    /**
     * 新增广告
     */
    public Map<String, Object> save(Advertisement advertisement) throws Exception;

    /**
     * 修改广告
     */
    public Map<String, Object> update(Advertisement advertisement) throws Exception;

    /**
     * 删除广告
     */
    public boolean delete(String id) throws Exception;

    /**
     * 根据业务唯一主键（队列【科室】编号）查询广告
     */
    public Advertisement getByQueuecode(String queueCode) throws Exception;

    /**
     * 查询最大广告编号
     */
    public int getMaxCode() throws Exception;

}
