package com.jfsoft.utils;

/**
 * 常量类
 */
public class Constants {

    /** session存储的用户key */
    public static final String SESSION_USER_KEY = "user_code";

    /** Controller返回状态码 */
    //成功
    public static final String RETURN_STATUS_SUCCESS = "success";
    //失败
    public static final String RETURN_STATUS_FAILURE = "failure";

    /** 是否使用：是 */
    public static final String IS_USE_TRUE = "1";
    public static final String IS_USE_FALSE = "0";
    /** 是否使用：全部 */
    public static final String IS_USE_ALL = "2";

    /** 是否删除：是 */
    public static final String IS_TRUE = "1";
    public static final String IS_FALSE = "0";

    /** 体检状态：检查中 */
    public static final String CHECK_STATUS_ING = "1";
    /** 体检状态：准备 */
    public static final String CHECK_STATUS_PREPARE = "2";
    /** 体检状态：等候 */
    public static final String CHECK_STATUS_WAIT = "3";
    /** 体检状态：已完成 */
    public static final String CHECK_STATUS_DONE = "4";

}
