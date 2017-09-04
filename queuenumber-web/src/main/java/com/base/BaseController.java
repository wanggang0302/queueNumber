package com.base;

import com.jfsoft.utils.Constants;

import javax.servlet.http.HttpSession;

/**
 * Controller的基类
 */
public class BaseController {

    /**
     * 保存code到session
     */
    protected void saveUserCodeToSession(HttpSession session, String userCode) {

        session.setAttribute(Constants.SESSION_USER_KEY, userCode);
    }

    /**
     * 获取code
     */
    protected String getUserCodeFromSession(HttpSession session) {
        String userCode = String.valueOf(session.getAttribute(Constants.SESSION_USER_KEY));
        return userCode;
    }

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

}
