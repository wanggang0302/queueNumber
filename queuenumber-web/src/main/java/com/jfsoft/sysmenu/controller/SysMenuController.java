package com.jfsoft.sysmenu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.base.BaseController;
import com.jfsoft.menu.service.ISysMenuService;
import com.jfsoft.model.SysMenu;
import com.jfsoft.model.SysQueue;
import com.jfsoft.utils.Constants;
import com.jfsoft.vo.NodeTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单管理
 */
@Controller
@RequestMapping("/sysmenu")
public class SysMenuController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 加载菜单
     * @author wanggang
     * 2017年3月14日 上午10:13:28
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMenu", method = RequestMethod.POST)
    public String getMenu(HttpSession session) {

        Map<String, Object> map = new HashMap<String, Object>();

        try {

            //针对一个用户对应多个角色的时候
            List<String> roleCode = getRoleCodeFromSession(session);

            logger.debug("加载菜单：roleCode is {} .", roleCode);

            //用户登录成功，查询用户菜单
            List<SysMenu> menuList = sysMenuService.findMenuList(roleCode);

            List<NodeTree> treeList = sysMenuService.getMenuTree(menuList);

            map.put("status", Constants.RETURN_STATUS_SUCCESS);
            map.put("data", treeList);

        } catch(Exception e) {
            map.put("status", Constants.RETURN_STATUS_FAILURE);
            map.put("data", e.getMessage());
            logger.error(e.getMessage(), e);
        }

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(SysMenu.class,
                "id", "code", "name", "parentCode", "url", "isuse");

        String resultJson = JSON.toJSONString(map, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return resultJson;
    }

}
