/**
 * SysUserrole.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-09-06 Created
 */
package com.jfsoft.model;

/**
 * 系统用户与角色关联关系表
 * 
 * @author wanggang
 * @version 1.0 2017-09-06
 */
public class SysUserrole {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 用户编号
     */
    private Integer usercode;

    /**
     * 角色编号
     */
    private Integer rolecode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsercode() {
        return usercode;
    }

    public void setUsercode(Integer usercode) {
        this.usercode = usercode;
    }

    public Integer getRolecode() {
        return rolecode;
    }

    public void setRolecode(Integer rolecode) {
        this.rolecode = rolecode;
    }

}
