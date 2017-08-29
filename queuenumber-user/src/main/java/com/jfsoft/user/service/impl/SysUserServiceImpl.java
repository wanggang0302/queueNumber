package com.jfsoft.user.service.impl;

import com.jfsoft.mapper.SysUserMapper;
import com.jfsoft.model.SysUser;
import com.jfsoft.user.service.ISysUserService;
import com.jfsoft.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SysUserServiceImpl implements ISysUserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SysUserMapper sysUserMapper;

    public SysUser findForAuthentication(String username, String password) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        //查询数据库
        SysUser sysUser = sysUserMapper.findByUsername(params);

        String md5Password = MD5Util.toMD5(password);
        if(null!=sysUser && !StringUtils.isBlank(sysUser.getPassword()) && md5Password.equals(sysUser.getPassword())) {
            return sysUser;
        }

        return null;
    }

}
