package com.jfsoft.basegroupitem.service.impl;

import com.alibaba.fastjson.JSON;
import com.jfsoft.basegroupitem.service.IBaseGroupitemService;
import com.jfsoft.mapper.BaseGroupitemMapper;
import com.jfsoft.model.BaseGroupitem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组合项目业务接口实现类
 */
@Service
public class BaseGroupitemServiceImpl implements IBaseGroupitemService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BaseGroupitemMapper baseGroupitemMapper;

    public List<BaseGroupitem> findPage(String currentPage, String pageSize, String name) throws Exception {

        //当前页码
        int currentPageInt = Integer.parseInt(currentPage);
        //每页显示的条数
        int pageSizeInt = Integer.parseInt(pageSize);
        //当前页开始的条数
        int pageStart = currentPageInt * pageSizeInt + 1;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("pageSize", pageSizeInt);
        params.put("pageStart", pageStart);

        List<BaseGroupitem> baseGroupitemList = baseGroupitemMapper.findPage(params);

        return baseGroupitemList;
    }

    public int findPageCount(String name) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);

        int count = baseGroupitemMapper.findPageCount(params);

        return count;
    }

    public boolean sync() throws Exception {

        //从C/S体检获取组合项目信息
        String json = "";

        if(StringUtils.isBlank(json)) {
            return false;
        }

        //将json解析成组合项目
        List<BaseGroupitem> baseGroupitemList = JSON.parseArray(json, BaseGroupitem.class);

        //保存到“排队叫号”系统中
        int count = baseGroupitemMapper.insertBatch(baseGroupitemList);

        if(count>0) {
            return true;
        }

        return false;
    }

}
