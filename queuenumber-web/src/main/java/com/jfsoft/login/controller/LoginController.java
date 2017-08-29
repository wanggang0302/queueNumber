package com.jfsoft.login.controller;

import com.jfsoft.model.SysUser;
import com.jfsoft.user.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 登录
     */
    @RequestMapping("")
    public String findPerCheckinfoListOfQueue(String username, String password) {

        logger.debug("controller findPerCheckinfoListOfQueue.");

        SysUser sysUser = sysUserService.findForAuthentication(username, password);

        return "index";
    }

}
