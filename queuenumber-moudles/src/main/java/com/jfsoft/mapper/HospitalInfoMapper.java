/**
 * HospitalInfoMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.HospitalInfo;

public interface HospitalInfoMapper {
    
    int deleteByPrimaryKey(String id);

    int insert(HospitalInfo record);

    int insertSelective(HospitalInfo record);

    HospitalInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HospitalInfo record);

    int updateByPrimaryKey(HospitalInfo record);
    
}