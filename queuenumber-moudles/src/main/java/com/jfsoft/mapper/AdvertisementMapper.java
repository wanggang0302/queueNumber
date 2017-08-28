/**
 * AdvertisementMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.Advertisement;

public interface AdvertisementMapper {
    
    int deleteByPrimaryKey(String id);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
    
}