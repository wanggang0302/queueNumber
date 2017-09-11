package com.jfsoft.sysuser.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.base.BaseController;
import com.jfsoft.model.SysQueue;
import com.jfsoft.model.SysUser;
import com.jfsoft.user.service.ISysUserService;
import com.jfsoft.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/sysuser")
public class SysUserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping(value="/toList", method=RequestMethod.GET)
    public String toList(ModelMap model) {

        try {
            //查询全部可用科室
            List<SysQueue> queueList = sysUserService.getAllSysQueue();
            model.addAttribute("queueList", queueList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/sysuser/list";
    }

    /**
     * 查询用户列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(String iDisplayStart, String iDisplayLength, String draw,
                       String name, String searchBeginTime, String searchEndTime, String queueCode) {

        Map<String, Object> result = new HashMap<String, Object>();

        List<SysUser> sysUserList = null;
        int pageCount = 0;

        try {
            sysUserList = sysUserService.findPage(iDisplayStart, iDisplayLength, name, searchBeginTime, searchEndTime, queueCode);
            pageCount = sysUserService.findPageCount(name, searchBeginTime, searchEndTime, queueCode);

            result.put("draw", draw);
            result.put("data", sysUserList);
            //表的总记录数
            result.put("recordsTotal", pageCount);
            //条件过滤后记录数
            result.put("recordsFiltered", pageCount);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            e.printStackTrace();
        }

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(SysUser.class,
                "id", "code", "name", "username", "sex", "tel", "email", "ownedqueue", "ownedQueueName", "isuse", "createtime");

        String resultJson = JSON.toJSONString(result, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return resultJson;
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(HttpSession session, Model model) {

        try {
            //查询全部可用科室
            List<SysQueue> queueList = sysUserService.getAllSysQueue();
            model.addAttribute("queueList", queueList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/sysuser/add";
    }

    /**
     * 新增用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpSession session, SysUser sysUser) {

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String userCode = getUserCodeFromSession(session);
            sysUser.setCreaterid(!StringUtils.isBlank(userCode)?Integer.parseInt(userCode):0);
            result = sysUserService.save(sysUser);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "保存失败！");
            e.printStackTrace();
        }

        String resultJson = JSON.toJSONString(result);

        return resultJson;
    }

    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
    public String toUpdate(HttpSession session, Model model, String code) {

        try {
            //查询全部可用科室
            List<SysQueue> queueList = sysUserService.getAllSysQueue();
            model.addAttribute("queueList", queueList);

            //查询用户详情
            SysUser sysUser = sysUserService.getByCode(code);
            model.addAttribute("sysUser", sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/sysuser/edit";
    }

    /**
     * 修改用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpSession session, SysUser sysUser) {

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = sysUserService.update(sysUser);
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "修改失败！");
            e.printStackTrace();
        }

        String resultJson = JSON.toJSONString(result);

        return resultJson;
    }

    /**
     * 删除用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpSession session, String id) {

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            boolean flage = sysUserService.delete(id);
            if(flage) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "删除用户成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "删除用户失败！");
            }
        } catch (Exception e) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "删除用户失败！");
            e.printStackTrace();
        }

        String resultJson = JSON.toJSONString(result);

        return resultJson;
    }

}
