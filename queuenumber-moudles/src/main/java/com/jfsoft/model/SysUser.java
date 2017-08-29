/**
 * SysUser.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 用户表
 * 
 * @author wanggang
 * @version 1.0 2017-08-28
 */
public class SysUser {

    /**
     */
    private Integer id;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码(MD5加密)
     */
    private String password;

    /**
     * 所属队列
     */
    private Integer ownedqueue;

    /**
     * 是否使用（0:否,1:是）
     */
    private Boolean isuse;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 创建人
     */
    private Integer createrid;

    /**
     * 备注
     */
    private String memo;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getOwnedqueue() {
        return ownedqueue;
    }

    public void setOwnedqueue(Integer ownedqueue) {
        this.ownedqueue = ownedqueue;
    }

    public Boolean getIsuse() {
        return isuse;
    }

    public void setIsuse(Boolean isuse) {
        this.isuse = isuse;
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

}
