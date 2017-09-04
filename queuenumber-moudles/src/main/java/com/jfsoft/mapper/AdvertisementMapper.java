/**
 * AdvertisementMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.Advertisement;

import java.util.List;
import java.util.Map;

public interface AdvertisementMapper {
    
    int deleteByPrimaryKey(String id);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);

    /**
     * 根据队列编号查询广告
     */
    Advertisement findByQueuecode(Map<String, Object> params);

    /**
     * 分页查询广告信息
     */
    List<Advertisement> findPage(Map<String, Object> params);

    /**
     * 查询广告总数
     */
    int findPageCount(Map<String, Object> params);

    /**
     * 查询最大广告编号
     */
    int getMaxCode();
    
}