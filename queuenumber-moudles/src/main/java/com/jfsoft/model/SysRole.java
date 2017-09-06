/**
 * SysRole.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-09-06 Created
 */
package com.jfsoft.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统角色表
 * 
 * @author wanggang
 * @version 1.0 2017-09-06
 */
public class SysRole {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 角色编号
     */
    private Integer code;

    /**
     * 角色名称
     */
    private String name;

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

    //一个角色对应多个权限
    private List<SysRolemenu> permissionList;

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

    public List<SysRolemenu> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysRolemenu> permissionList) {
        this.permissionList = permissionList;
    }

    public List<String> getPermissionsName() {
        List<String> list = new ArrayList<String>();
        List<SysRolemenu> perlist = getPermissionList();
        for (SysRolemenu per : perlist) {
            list.add(null!=per.getMenucode()?per.getMenucode().toString():"");
        }
        return list;
    }

}
