package com.base;

import com.jfsoft.model.SysRole;
import com.jfsoft.utils.Constants;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller的基类
 */
public class BaseController {

    /**
     * 保存id到session
     */
    protected void saveUserIdToSession(HttpSession session, String userId) {

        session.setAttribute(Constants.SESSION_USERID_KEY, userId);
    }

    /**
     * 获取id
     */
    protected String getUserIdFromSession(HttpSession session) {
        String userCode = String.valueOf(session.getAttribute(Constants.SESSION_USERID_KEY));
        return userCode;
    }

    /**
     * 保存code到session
     */
    protected void saveUserCodeToSession(HttpSession session, String userCode) {

        session.setAttribute(Constants.SESSION_USERCODE_KEY, userCode);
    }

    /**
     * 获取code
     */
    protected String getUserCodeFromSession(HttpSession session) {
        String userCode = String.valueOf(session.getAttribute(Constants.SESSION_USERCODE_KEY));
        return userCode;
    }

    /**
     * 保存username到session
     */
    protected void saveUsernameToSession(HttpSession session, String userCode) {

        session.setAttribute(Constants.SESSION_USERNAME_KEY, userCode);
    }

    /**
     * 获取username
     */
    protected String getUsernameFromSession(HttpSession session) {
        String username = String.valueOf(session.getAttribute(Constants.SESSION_USERNAME_KEY));
        return username;
    }

    /**
     * 保存name到session
     */
    protected void saveNameToSession(HttpSession session, String userCode) {

        session.setAttribute(Constants.SESSION_NAME_KEY, userCode);
    }

    /**
     * 获取name
     */
    protected String getNameFromSession(HttpSession session) {
        String name = String.valueOf(session.getAttribute(Constants.SESSION_NAME_KEY));
        return name;
    }

    /**
     * 保存queueCode到session
     */
    protected void saveQueueCodeToSession(HttpSession session, String queueCode) {

        session.setAttribute(Constants.SESSION_QUEUE_KEY, queueCode);
    }

    /**
     * 获取queueCode
     */
    protected String getQueueCodeFromSession(HttpSession session) {
        String userCode = String.valueOf(session.getAttribute(Constants.SESSION_QUEUE_KEY));
        return userCode;
    }

    /**
     * 保存roleCode到session
     */
    protected void saveRoleCodeToSession(HttpSession session, List<SysRole> sysRoleList) {

        List<String> roleCodeList = new ArrayList<String>();
        for(SysRole r : sysRoleList) {
            roleCodeList.add(Integer.toString(r.getCode()));
        }

        session.setAttribute(Constants.SESSION_ROLE_KEY, roleCodeList);
    }

    /**
     * 获取roleCode
     */
    protected List<String> getRoleCodeFromSession(HttpSession session) {
        List<String> roleCodeList = (List<String>) session.getAttribute(Constants.SESSION_ROLE_KEY);
        return roleCodeList;
    }

}
