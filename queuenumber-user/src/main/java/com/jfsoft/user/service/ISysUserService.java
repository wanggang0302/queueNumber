package com.jfsoft.user.service;

import com.jfsoft.model.SysUser;

public interface ISysUserService {

    /**
     * 验证用户名、密码
     * @param username
     * @param password
     * @return
     */
    public SysUser findForAuthentication(String username, String password);

}
