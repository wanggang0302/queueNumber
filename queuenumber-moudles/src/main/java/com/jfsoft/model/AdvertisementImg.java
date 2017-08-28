/**
 * AdvertisementImg.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-28 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 广告图片附件表
 * 
 * @author wanggang
 * @version 1.0 2017-08-28
 */
public class AdvertisementImg {

    /**
     */
    private String id;

    /**
     * 广告编号
     */
    private Integer adcode;

    /**
     * 图片名称
     */
    private String imgname;

    /**
     * 图片路径(相对路径)
     */
    private String imgpath;

    /**
     * 图片格式
     */
    private String imgformat;

    /**
     * 图片大小(B字节)
     */
    private Integer imgsize;

    /**
     * 上传时间
     */
    private Date createtime;

    /**
     * 上传用户ID
     */
    private Integer createrid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getAdcode() {
        return adcode;
    }

    public void setAdcode(Integer adcode) {
        this.adcode = adcode;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname == null ? null : imgname.trim();
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath == null ? null : imgpath.trim();
    }

    public String getImgformat() {
        return imgformat;
    }

    public void setImgformat(String imgformat) {
        this.imgformat = imgformat == null ? null : imgformat.trim();
    }

    public Integer getImgsize() {
        return imgsize;
    }

    public void setImgsize(Integer imgsize) {
        this.imgsize = imgsize;
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
