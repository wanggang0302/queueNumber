/**
 * BaseQueuegroupmap.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.model;

/**
 * 队列与组合项目关联关系表
 * 
 * @author wanggang
 * @version 1.0 2017-08-28
 */
public class BaseQueuegroupmap {

    /**
     */
    private String id;

    /**
     * 队列编号
     */
    private Integer queuecode;

    /**
     * 组合项目编号
     */
    private Integer groupitemcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getQueuecode() {
        return queuecode;
    }

    public void setQueuecode(Integer queuecode) {
        this.queuecode = queuecode;
    }

    public Integer getGroupitemcode() {
        return groupitemcode;
    }

    public void setGroupitemcode(Integer groupitemcode) {
        this.groupitemcode = groupitemcode;
    }

}
