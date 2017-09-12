/**
 * CallingRule.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-09-12 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 呼号器角色，用来设置呼号规则
 *
 * @author wanggang
 * @version 1.0 2017-09-12
 */
public class CallingRule {

    /**
     */
    private Integer id;

    /**
     * 编号
     */
    private Integer code;

    /**
     * 性别优先级(1:男优先,2:女优先,3:无）
     */
    private String sexpriority;

    /**
     * 是否优先VIP队列(0:否,1:是)
     */
    private String isvip;

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

    public String getSexpriority() {
        return sexpriority;
    }

    public void setSexpriority(String sexpriority) {
        this.sexpriority = sexpriority == null ? null : sexpriority.trim();
    }

    public String getIsvip() {
        return isvip;
    }

    public void setIsvip(String isvip) {
        this.isvip = isvip == null ? null : isvip.trim();
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
