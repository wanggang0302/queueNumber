package com.jfsoft.login.controller;

import com.base.BaseController;
import com.jfsoft.model.SysUser;
import com.jfsoft.user.service.ISysUserService;
import com.jfsoft.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 登录
 */
@Controller
public class LoginController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String toLogin() {

        logger.debug("to login page!");

        return "login";
    }

    /**
     * 登录验证
     */
    //@RequestMapping(value="/login", method=RequestMethod.POST)
    //public String findPerCheckinfoListOfQueue(HttpSession session, ModelMap model, String username, String password) {
    //
    //    SysUser sysUser = null;
    //    try {
    //        sysUser = sysUserService.findForAuthentication(username, password);
    //
    //        if(null!=sysUser) {
    //            //用户所属队列（科室）
    //            Integer queueCode = sysUser.getOwnedqueue();
    //            model.put("queueCode", queueCode);
    //            saveQueueCodeToSession(session, Integer.toString(queueCode));
    //            saveUserCodeToSession(session, Integer.toString(sysUser.getCode()));
    //
    //            //return "/doctor/queue/list";
    //            return "index";
    //        }
    //    } catch (Exception e) {
    //
    //        logger.error(e.getMessage(), e);
    //        e.printStackTrace();
    //    }
    //
    //    model.put("msg", "登录失败，用户名或密码错误！");
    //
    //    return "redirect:/login";
    //}

    /**
     * 登录验证用户名&密码
     */
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@Valid SysUser user, HttpSession session, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return "login";
        }

        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Util.toMD5(password));
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(IncorrectCredentialsException ice){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        }catch(LockedAccountException lae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");

            try {
                SysUser sysUser = sysUserService.findForAuthentication(username, password);

                saveUserCodeToSession(session, Integer.toString(sysUser.getCode()));
                saveRoleCodeToSession(session, sysUser.getRoleList());
                saveUsernameToSession(session, sysUser.getUsername());
                saveNameToSession(session, sysUser.getName());

                return "index";
            } catch (Exception e) {
                logger.error("Error occured when getting SysUser by username: {} .", username, e);
                return "redirect:/login";
            }
        }else{
            token.clear();
        }
        return "redirect:/login";
    }

    @RequestMapping(value="/logout", method= RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        logger.info("------没有权限-------");
        return "403";
    }

}
