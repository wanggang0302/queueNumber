package com.jfsoft.login.controller;

import com.base.BaseController;
import com.jfsoft.model.SysUser;
import com.jfsoft.user.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
    public String findPerCheckinfoListOfQueue(HttpSession session, ModelMap model, String username, String password) {

        SysUser sysUser = null;
        try {
            sysUser = sysUserService.findForAuthentication(username, password);

            if(null!=sysUser) {
                //用户所属队列（科室）
                Integer queueCode = sysUser.getOwnedqueue();
                model.put("queueCode", queueCode);
                saveQueueCodeToSession(session, Integer.toString(queueCode));
                saveUserCodeToSession(session, Integer.toString(sysUser.getCode()));

                return "/doctor/queue/list";
            }
        } catch (Exception e) {

            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        model.put("msg", "登录失败，用户名或密码错误！");

        return "/doctor/login";
    }

}
