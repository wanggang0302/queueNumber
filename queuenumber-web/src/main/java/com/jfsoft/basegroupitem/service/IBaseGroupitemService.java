package com.jfsoft.basegroupitem.service;

import com.jfsoft.model.BaseGroupitem;

import java.util.List;

/**
 * 组合项目业务接口
 */
public interface IBaseGroupitemService {

    /**
     * 分页查询组合项目
     */
    public List<BaseGroupitem> findPage(String currentPage, String pageSize, String name) throws Exception;

    /**
     * 查询组合项目总数
     */
    public int findPageCount(String name) throws Exception;

    /**
     * 同步C/S体检的组合项目数据
     */
    public boolean sync() throws Exception;

}
