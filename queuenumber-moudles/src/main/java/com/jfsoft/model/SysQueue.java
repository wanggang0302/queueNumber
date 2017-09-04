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
    private String isneedqueueup;

    /**
     * 需要排队,是否启用排队(0:否,1:是)
     */
    private String isenable;

    /**
     * 检查一个人需要的时间(单位:分钟)
     */
    private Float queuetime;

    /**
     * 是否显示广告(0:否,1:是)
     */
    private String isshowad;

    /**
     * 是否有前置条件(0:否,1:是)
     */
    private String isperterm;

    /**
     * 前置条件详情
     */
    private String perterminfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private String createId;

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

    public Float getQueuetime() {
        return queuetime;
    }

    public void setQueuetime(Float queuetime) {
        this.queuetime = queuetime;
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

    public String getIsneedqueueup() {
        return isneedqueueup;
    }

    public void setIsneedqueueup(String isneedqueueup) {
        this.isneedqueueup = isneedqueueup;
    }

    public String getIsenable() {
        return isenable;
    }

    public void setIsenable(String isenable) {
        this.isenable = isenable;
    }

    public String getIsshowad() {
        return isshowad;
    }

    public void setIsshowad(String isshowad) {
        this.isshowad = isshowad;
    }

    public String getIsperterm() {
        return isperterm;
    }

    public void setIsperterm(String isperterm) {
        this.isperterm = isperterm;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
}
