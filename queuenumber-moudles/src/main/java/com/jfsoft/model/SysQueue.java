/**
 * SysQueue.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.model;

import com.jfsoft.vo.PerCheckinfo;

import java.util.Date;
import java.util.List;

/**
 * 队列信息表
 * 
 * @author wanggang
 * @version 1.0 2017-08-28
 */
public class SysQueue {

    /**
     */
    private Integer id;

    /**
     * 队列编号
     */
    private Integer code;

    /**
     * 队列名称
     */
    private String name;

    /**
     * 排队位置说明
     */
    private String queuelocation;

    /**
     * 是否需要排队(0:否,1:是)
     */
    private Boolean isneedqueueup;

    /**
     * 需要排队,是否启用排队(0:否,1:是)
     */
    private Boolean isenable;

    /**
     * 检查一个人需要的时间(单位:分钟)
     */
    private Float queuetime;

    /**
     * 是否显示广告(0:否,1:是)
     */
    private Boolean isshowad;

    /**
     * 是否有前置条件(0:否,1:是)
     */
    private Boolean isperterm;

    /**
     * 前置条件详情
     */
    private String perterminfo;

    /**
     * 体检者队列
     */
    private List<PerCheckinfo> perCheckinfoList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getQueuelocation() {
        return queuelocation;
    }

    public void setQueuelocation(String queuelocation) {
        this.queuelocation = queuelocation == null ? null : queuelocation.trim();
    }

    public Boolean getIsneedqueueup() {
        return isneedqueueup;
    }

    public void setIsneedqueueup(Boolean isneedqueueup) {
        this.isneedqueueup = isneedqueueup;
    }

    public Boolean getIsenable() {
        return isenable;
    }

    public void setIsenable(Boolean isenable) {
        this.isenable = isenable;
    }

    public Float getQueuetime() {
        return queuetime;
    }

    public void setQueuetime(Float queuetime) {
        this.queuetime = queuetime;
    }

    public Boolean getIsshowad() {
        return isshowad;
    }

    public void setIsshowad(Boolean isshowad) {
        this.isshowad = isshowad;
    }

    public Boolean getIsperterm() {
        return isperterm;
    }

    public void setIsperterm(Boolean isperterm) {
        this.isperterm = isperterm;
    }

    public String getPerterminfo() {
        return perterminfo;
    }

    public void setPerterminfo(String perterminfo) {
        this.perterminfo = perterminfo == null ? null : perterminfo.trim();
    }

    public List<PerCheckinfo> getPerCheckinfoList() {
        return perCheckinfoList;
    }

    public void setPerCheckinfoList(List<PerCheckinfo> perCheckinfoList) {
        this.perCheckinfoList = perCheckinfoList;
    }

}
