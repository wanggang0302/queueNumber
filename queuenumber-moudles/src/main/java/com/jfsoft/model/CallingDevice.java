/**
 * CallingDevice.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 呼叫器设置
 * 
 * @author wanggang
 * @version 1.0 2017-08-28
 */
public class CallingDevice {

    /**
     */
    private Integer id;

    /**
     * 队列编码
     */
    private Integer queuecode;

    /**
     * 是否VIP队列呼叫器(0:否,1:是)
     */
    private Boolean isvip;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQueuecode() {
        return queuecode;
    }

    public void setQueuecode(Integer queuecode) {
        this.queuecode = queuecode;
    }

    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
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
