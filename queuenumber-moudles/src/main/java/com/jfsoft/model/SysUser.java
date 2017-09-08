/**
 * SysUser.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * 性别
     */
    private String sex;

    /**
     * 手机
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所属队列
     */
    private Integer ownedqueue;

    /**
     * 所属队列名称
     */
    private String ownedQueueName;

    /**
     * 是否使用（0:否,1:是）
     */
    private String isuse;

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

    /**
     * 用户的角色集合
     */
    private List<SysRole> roleList;

    public String getOwnedQueueName() {
        return ownedQueueName;
    }

    public void setOwnedQueueName(String ownedQueueName) {
        this.ownedQueueName = ownedQueueName;
    }

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

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
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

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public Set<String> getRolesName() {
        List<SysRole> roles = getRoleList();
        Set<String> set = new HashSet<String>();
        for (SysRole role : roles) {
            set.add(role.getName());
        }
        return set;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
