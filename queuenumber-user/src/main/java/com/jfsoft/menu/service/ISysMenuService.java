package com.jfsoft.menu.service;

import com.jfsoft.model.SysMenu;
import com.jfsoft.vo.NodeTree;

import java.util.List;

/**
 * 菜单业务接口实现类
 */
public interface ISysMenuService {

    /**
     * 根据角色查询菜单
     */
    public List<SysMenu> findMenuList(List<String> roleCodeList) throws Exception;

    /**
     * 获得菜单tree
     */
    public List<NodeTree> getMenuTree(List<SysMenu> menuList) throws Exception;

    /**
     * 加载系统菜单
     */
    public NodeTree setTree(NodeTree parentNode, List<SysMenu> list) throws Exception;

}
