/**
 * HospitalInfo.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 医院基本信息表
 * 
 * @author wanggang
 * @version 1.0 2017-08-28
 */
public class HospitalInfo {

    /**
     */
    private Integer id;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 医院名称
     */
    private String name;

    /**
     * 联系电话
     */
    private String linktel;

    /**
     * 医院简介
     */
    private String introduction;

    /**
     * 修改时间
     */
    private Date modifytime;

    /**
     * 修改人
     */
    private Integer modifierid;

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

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel == null ? null : linktel.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getModifierid() {
        return modifierid;
    }

    public void setModifierid(Integer modifierid) {
        this.modifierid = modifierid;
    }

}
