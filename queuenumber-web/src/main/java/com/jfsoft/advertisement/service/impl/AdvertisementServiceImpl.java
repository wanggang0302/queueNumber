package com.jfsoft.advertisement.service.impl;

import com.jfsoft.advertisement.service.IAdvertisementService;
import com.jfsoft.mapper.AdvertisementMapper;
import com.jfsoft.model.Advertisement;
import com.jfsoft.model.SysUser;
import com.jfsoft.utils.Constants;
import com.jfsoft.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 广告管理业务接口实现类
 */
@Service
public class AdvertisementServiceImpl implements IAdvertisementService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdvertisementMapper advertisementMapper;

    public List<Advertisement> findPage(String currentPage, String pageSize, String queueCode) throws Exception {

        //当前页码
        int currentPageInt = Integer.parseInt(currentPage);
        //每页显示的条数
        int pageSizeInt = Integer.parseInt(pageSize);
        //当前页开始的条数
        int pageStart = currentPageInt * pageSizeInt + 1;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("queueCode", queueCode);
        params.put("pageSize", pageSizeInt);
        params.put("pageStart", pageStart);

        List<Advertisement> advertisementList = advertisementMapper.findPage(params);

        return advertisementList;
    }

    public int findPageCount(String queueCode) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("queueCode", queueCode);

        int advertisementCount = advertisementMapper.findPageCount(params);

        return advertisementCount;
    }

    public Map<String, Object> save(Advertisement advertisement) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();

        //队列（科室）编号
        Integer queueCode = advertisement.getQueuecode();
        Advertisement advertisementExist = getByQueuecode(queueCode.toString());
        if(null!=advertisementExist) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "广告已经存在，不允许重复添加！");
        } else {
            int maxCode = getMaxCode();
            advertisement.setCode(maxCode + 1);
            advertisement.setIsuse(Constants.IS_TRUE);
            advertisement.setCreatetime(new Date());
            int count = advertisementMapper.insertSelective(advertisement);
            if(count>0) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "广告添加成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "广告添加失败！");
            }
        }

        return result;
    }

    public Map<String, Object> update(Advertisement advertisement) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();

        //队列（科室）编号
        Integer queueCode = advertisement.getQueuecode();
        Advertisement advertisementExist = getByQueuecode(queueCode.toString());
        if(null!=advertisementExist) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "广告已经存在，不允许重复添加！");
        } else {
            int count = advertisementMapper.updateByPrimaryKeySelective(advertisement);
            if(count>0) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "修改广告成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "修改广告失败！");
            }
        }

        return result;
    }

    public boolean delete(String id) throws Exception {
        int count = advertisementMapper.deleteByPrimaryKey(id);
        if(count>0) {
            return true;
        }
        return false;
    }

    public Advertisement getByQueuecode(String queueCode) throws Exception {

        Advertisement advertisement = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("queueCode", queueCode);

        //查询数据库
        advertisement = advertisementMapper.findByQueuecode(params);

        return advertisement;
    }

    public int getMaxCode() throws Exception {

        int maxCode = advertisementMapper.getMaxCode();

        return maxCode;
    }

}
