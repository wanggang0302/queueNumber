/**
 * Advertisement.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 广告表
 * 
 * @author wanggang
 * @version 1.0 2017-08-28
 */
public class Advertisement {

    /**
     */
    private Integer id;

    /**
     * 编号
     */
    private Integer code;

    /**
     * 队列编号
     */
    private Integer queuecode;

    /**
     * 是否使用(0:否,1:是)
     */
    private Boolean isuse;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 创建人ID
     */
    private Integer createrid;

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

    public Integer getQueuecode() {
        return queuecode;
    }

    public void setQueuecode(Integer queuecode) {
        this.queuecode = queuecode;
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

}
