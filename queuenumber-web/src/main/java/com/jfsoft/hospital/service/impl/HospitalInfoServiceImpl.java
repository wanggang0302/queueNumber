package com.jfsoft.hospital.service.impl;

import com.jfsoft.hospital.service.IHospitalInfoService;
import com.jfsoft.mapper.HospitalInfoMapper;
import com.jfsoft.model.HospitalInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 医院信息管理业务接口实现类
 */
@Service
public class HospitalInfoServiceImpl implements IHospitalInfoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HospitalInfoMapper hospitalInfoMapper;

    public HospitalInfo getHospitalInfo(String id) throws Exception {

        HospitalInfo hospitalInfo = hospitalInfoMapper.selectByPrimaryKey(id);

        return hospitalInfo;
    }

    public boolean saveHospitalInfo(HospitalInfo hospitalInfo) throws Exception {

        HospitalInfo exist = getHospitalInfo(hospitalInfo.getId().toString());

        //被修改的条数
        int count = 0;

        if(null!=exist) {

            hospitalInfo.setModifytime(new Date());
            count = hospitalInfoMapper.updateByPrimaryKeySelective(hospitalInfo);
        } else {
            count = hospitalInfoMapper.insertSelective(hospitalInfo);
        }

        if(count>0) {
            return true;
        }

        return false;
    }

}
