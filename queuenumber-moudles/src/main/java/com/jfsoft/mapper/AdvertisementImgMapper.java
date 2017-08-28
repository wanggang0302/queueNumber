/**
 * AdvertisementImgMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.AdvertisementImg;

public interface AdvertisementImgMapper {
    
    int insert(AdvertisementImg record);

    int insertSelective(AdvertisementImg record);
    
}