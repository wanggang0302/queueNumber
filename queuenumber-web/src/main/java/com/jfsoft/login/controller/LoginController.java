package com.jfsoft.login.controller;

import com.base.BaseController;
import com.jfsoft.model.SysUser;
import com.jfsoft.user.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登录
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping()
    public String toLogin() {

        logger.debug("to login page!");

        return "doctor/login";
    }

    /**
     * 登录验证
     */
    @RequestMapping(method = RequestMethod.POST)
    public String findPerCheckinfoListOfQueue(String username, String password) {

        logger.debug("controller findPerCheckinfoListOfQueue.");

        SysUser sysUser = sysUserService.findForAuthentication(username, password);

        return "/doctor/queue/list";
    }

}
