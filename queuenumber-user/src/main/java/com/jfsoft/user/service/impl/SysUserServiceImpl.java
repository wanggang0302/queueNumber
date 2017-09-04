package com.jfsoft.user.service.impl;

import com.jfsoft.mapper.SysUserMapper;
import com.jfsoft.model.SysUser;
import com.jfsoft.user.service.ISysUserService;
import com.jfsoft.utils.Constants;
import com.jfsoft.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements ISysUserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SysUserMapper sysUserMapper;

    public List<SysUser> findPage(String currentPage, String pageSize, String name, String username, String queueCode) throws Exception {

        //当前页码
        int currentPageInt = Integer.parseInt(currentPage);
        //每页显示的条数
        int pageSizeInt = Integer.parseInt(pageSize);
        //当前页开始的条数
        int pageStart = currentPageInt * pageSizeInt + 1;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("username", username);
        params.put("ownedqueue", queueCode);
        params.put("pageSize", pageSizeInt);
        params.put("pageStart", pageStart);

        List<SysUser> sysUserList = sysUserMapper.findPage(params);

        return sysUserList;
    }

    public int findPageCount(String name, String username, String queueCode) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("username", username);
        params.put("ownedqueue", queueCode);

        int userCount = sysUserMapper.findPageCount(params);

        return userCount;
    }

    public Map<String, Object> save(SysUser sysUser) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();

        //用户账号
        String username = sysUser.getUsername();
        SysUser sysUserExist = getByUsername(username);
        if(null!=sysUserExist) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "已经存在该用户，不允许重复添加！");
        } else {
            int maxCode = getMaxCode();
            sysUser.setCode(maxCode + 1);
            sysUser.setPassword(MD5Util.toMD5(username + Constants.DEFAULT_PASSWORD));
            sysUser.setIsuse(Constants.IS_TRUE);
            sysUser.setCreatetime(new Date());
            int count = sysUserMapper.insertSelective(sysUser);
            if(count>0) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "保存成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "保存失败！");
            }
        }

        return result;
    }

    public Map<String, Object> update(SysUser sysUser) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();

        //用户账号
        String username = sysUser.getUsername();
        SysUser sysUserExist = getByUsername(username);
        if(null!=sysUserExist) {
            result.put("status", Constants.RETURN_STATUS_FAILURE);
            result.put("data", "已经存在该用户，不允许重复添加！");
        } else {
            int count = sysUserMapper.updateByPrimaryKeySelective(sysUser);
            if(count>0) {
                result.put("status", Constants.RETURN_STATUS_SUCCESS);
                result.put("data", "修改成功！");
            } else {
                result.put("status", Constants.RETURN_STATUS_FAILURE);
                result.put("data", "修改失败！");
            }
        }

        return result;
    }

    public boolean delete(String id) throws Exception {
        int count = sysUserMapper.deleteByPrimaryKey(id);
        if(count>0) {
            return true;
        }
        return false;
    }

    public SysUser getByUsername(String username) throws Exception {

        SysUser sysUser = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        //查询数据库
        sysUser = sysUserMapper.findByUsername(params);

        return sysUser;
    }

    public SysUser findForAuthentication(String username, String password) throws Exception {

        //查询数据库
        SysUser sysUser = getByUsername(username);

        String md5Password = MD5Util.toMD5(password);
        if(null!=sysUser && !StringUtils.isBlank(sysUser.getPassword()) && md5Password.equals(sysUser.getPassword())) {
            return sysUser;
        }

        return null;
    }

    public int getMaxCode() throws Exception {

        int maxCode = sysUserMapper.getMaxCode();

        return maxCode;
    }

}
