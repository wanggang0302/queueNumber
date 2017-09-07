package com.jfsoft.hospital.controller;

import com.base.BaseController;
import com.jfsoft.hospital.service.IHospitalInfoService;
import com.jfsoft.model.HospitalInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
    public String getHospitalInfo(ModelMap model, @ModelAttribute("id") String id, @ModelAttribute("code") String code) {

        HospitalInfo hospitalInfo = null;

        try {

            hospitalInfo = hospitalInfoService.getHospitalInfo(id);
            hospitalInfo.setCode(Integer.parseInt(code));
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.put("hospital", hospitalInfo);
        model.put("refreshUrl", "/hospital/getHospitalInfo?id=" + id + "&code=" + code);

        return "/hospital/hospital_base";
    }

    /**
     * 保存医院信息
     */
    @RequestMapping(value = "/saveHospitalInfo", method = RequestMethod.POST)
    public String saveHospitalInfo(HttpSession session, ModelMap model, RedirectAttributes redirect, HospitalInfo hospitalInfo) {

        boolean flage = true;

        try {
            Integer userId = getUserIdFromSession(session);
            hospitalInfo.setModifierid(userId);
            flage = hospitalInfoService.saveHospitalInfo(hospitalInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        redirect.addFlashAttribute("id", hospitalInfo.getId());
        redirect.addFlashAttribute("code", hospitalInfo.getCode());

        return "redirect:/hospital/getHospitalInfo";
    }

}
