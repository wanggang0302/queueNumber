package com.jfsoft.doctor.service;

import java.util.Map;

/**
 * 医生呼号业务接口
 */
public interface IDoctorService {

    /**
     * 上一个体检者
     */
    public Map<String, Object> previous(String queueCode, String isVip, String currentTestno) throws Exception;

    /**
     * 跳过
     */
    public Map<String, Object> skip(String queueCode, String isVip, String currentTestno) throws Exception;

    /**
     * 医生呼号
     */
    public String wentTo(String queueCode, String isVip, String testno, String deviceNo) throws Exception;

    /**
     * 将体检者移除队列
     */
    public boolean complete(String queueCode, String isVip, String testno) throws Exception;

}
