package com.jfsoft.utils;

/**
 * 常量类
 */
public class Constants {

    /** session存储的用户IDkey */
    public static final String SESSION_USERID_KEY = "user_id";
    /** session存储的用户key */
    public static final String SESSION_USERCODE_KEY = "user_code";
    /** session存储的用户key */
    public static final String SESSION_USERNAME_KEY = "user_name";
    /** session存储的用户key */
    public static final String SESSION_NAME_KEY = "name";
    /** session存储的queueCode key */
    public static final String SESSION_QUEUE_KEY = "queueCode";
    /** session存储的roleCode key */
    public static final String SESSION_ROLE_KEY = "roleCode";
    /** 用户默认密码 */
    public static final String DEFAULT_PASSWORD = "123456";

    /** Controller返回状态码 */
    //成功
    public static final String RETURN_STATUS_SUCCESS = "success";
    //失败
    public static final String RETURN_STATUS_FAILURE = "failure";

    /** 是否：是 */
    public static final String IS_TRUE = "1";
    public static final String IS_FALSE = "0";
    /** 是否：全部 */
    public static final String IS_ALL = "2";

    /** 系统菜单根节点Code */
    public static final String SYS_MENU_ROOT_CODE = "1";

    /** 体检状态：检查中 */
    public static final String CHECK_STATUS_ING = "1";
    /** 体检状态：准备 */
    public static final String CHECK_STATUS_PREPARE = "2";
    /** 体检状态：等候 */
    public static final String CHECK_STATUS_WAIT = "3";
    /** 体检状态：已完成 */
    public static final String CHECK_STATUS_DONE = "4";

}
