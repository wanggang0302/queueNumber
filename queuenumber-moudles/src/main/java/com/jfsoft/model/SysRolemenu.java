/**
 * SysRolemenu.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-09-06 Created
 */
package com.jfsoft.model;

/**
 * 系统角色与菜单关联关系表
 * 
 * @author wanggang
 * @version 1.0 2017-09-06
 */
public class SysRolemenu {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 角色编号
     */
    private Integer rolecode;

    /**
     * 菜单编号
     */
    private Integer menucode;

    /**
     * 权限
     */
    private String permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRolecode() {
        return rolecode;
    }

    public void setRolecode(Integer rolecode) {
        this.rolecode = rolecode;
    }

    public Integer getMenucode() {
        return menucode;
    }

    public void setMenucode(Integer menucode) {
        this.menucode = menucode;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}
