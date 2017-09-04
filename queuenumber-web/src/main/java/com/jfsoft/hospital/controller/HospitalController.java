package com.jfsoft.hospital.controller;

import com.base.BaseController;
import com.jfsoft.hospital.service.IHospitalInfoService;
import com.jfsoft.model.HospitalInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 医院信息管理
 */
@Controller
@RequestMapping("/hospital")
public class HospitalController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IHospitalInfoService hospitalInfoService;

    /**
     * 获得医院信息
     */
    @RequestMapping(value = "/getHospitalInfo", method = RequestMethod.GET)
    public String getHospitalInfo(ModelMap model, String id, String code) {

        HospitalInfo hospitalInfo = null;

        try {

            hospitalInfo = hospitalInfoService.getHospitalInfo(id);
            hospitalInfo.setCode(Integer.parseInt(code));
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.put("hospital", hospitalInfo);

        return "/hospital/info";
    }

    /**
     * 保存医院信息
     */
    @RequestMapping(value = "/saveHospitalInfo", method = RequestMethod.POST)
    public String saveHospitalInfo(ModelMap model, HospitalInfo hospitalInfo) {

        boolean flage = true;

        try {

            flage = hospitalInfoService.saveHospitalInfo(hospitalInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.put("saveFlage", flage);

        return "/hospital/info";
    }

}
