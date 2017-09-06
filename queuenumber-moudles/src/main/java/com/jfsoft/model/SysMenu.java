/**
 * SysMenu.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-09-06 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 系统菜单表
 * 
 * @author wanggang
 * @version 1.0 2017-09-06
 */
public class SysMenu {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 菜单编号
     */
    private Integer code;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父级菜单编号
     */
    private Integer parentCode;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 是否使用（0:否,1:是）
     */
    private String isuse;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 创建人ID
     */
    private Integer createrid;

    /**
     * 备注
     */
    private String memo;

    /**
     * 该菜单被授予的权限
     */
    private String permission;

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

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse == null ? null : isuse.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCreaterid() {
        return createrid;
    }

    public void setCreaterid(Integer createrid) {
        this.createrid = createrid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}
