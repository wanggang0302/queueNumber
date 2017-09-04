package com.jfsoft.hospital.service;

import com.jfsoft.model.HospitalInfo;

/**
 * 医院信息管理业务接口
 */
public interface IHospitalInfoService {

    /**
     * 查询医院信息
     */
    public HospitalInfo getHospitalInfo(String id) throws Exception;

    /**
     * 保存医院信息
     */
    public boolean saveHospitalInfo(HospitalInfo hospitalInfo) throws Exception;

}
