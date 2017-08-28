/**
 * BaseGroupitem.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 组合项目表(同步的C/S体检系统的)
 * 
 * @author wanggang
 * @version 1.0 2017-08-28
 */
public class BaseGroupitem {

    /**
     */
    private String id;

    /**
     * 组合项目编号，同步的C/S体检系统给的
     */
    private Integer code;

    /**
     * 组合项目名称
     */
    private String name;

    /**
     * 科室code
     */
    private Integer deptcode;

    /**
     * 科室名称
     */
    private String deptname;

    /**
     * 是否使用(0:否,1:是)
     */
    private Boolean isuse;

    /**
     * 同步时间
     */
    private Date synctime;

    /**
     * 同步人
     */
    private Integer syncuserid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Integer getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(Integer deptcode) {
        this.deptcode = deptcode;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public Boolean getIsuse() {
        return isuse;
    }

    public void setIsuse(Boolean isuse) {
        this.isuse = isuse;
    }

    public Date getSynctime() {
        return synctime;
    }

    public void setSynctime(Date synctime) {
        this.synctime = synctime;
    }

    public Integer getSyncuserid() {
        return syncuserid;
    }

    public void setSyncuserid(Integer syncuserid) {
        this.syncuserid = syncuserid;
    }

}
