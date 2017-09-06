package com.jfsoft.menu.service.impl;

import com.jfsoft.mapper.SysMenuMapper;
import com.jfsoft.menu.service.ISysMenuService;
import com.jfsoft.model.SysMenu;
import com.jfsoft.utils.Constants;
import com.jfsoft.vo.NodeTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单业务接口实现类
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SysMenuMapper sysMenuMapper;

    public List<SysMenu> findMenuList(List<String> roleCodeList) throws Exception {

        List<SysMenu> sysMenuList = new ArrayList<SysMenu>();

        Map<String, Object> param = new HashMap<String, Object>();
        if(null!=roleCodeList && roleCodeList.size()>0) {
            param.put("roleCodes", roleCodeList.toArray());

            sysMenuList = sysMenuMapper.findMenuByRole(param);
        }

        return sysMenuList;
    }

    public List<NodeTree> getMenuTree(List<SysMenu> menuList) throws Exception {

        NodeTree tree = new NodeTree();
        SysMenu root = new SysMenu();
        root.setCode(Integer.parseInt(Constants.SYS_MENU_ROOT_CODE));
        tree.setNode(root);
        if(null!=menuList && menuList.size()>0) {
            tree = setTree(tree, menuList);
        }
        return tree.getList();
    }

    public NodeTree setTree(NodeTree parentNode, List<SysMenu> list) throws Exception {
        SysMenu menu = (SysMenu) parentNode.getNode();
        long menuCode = menu.getCode();
        for(SysMenu pr:list){
            if(null!=pr && null!=pr.getParentCode() && pr.getParentCode()==menuCode){
                NodeTree node = new NodeTree();
                node.setNode(pr);
                parentNode.add(node);
                this.setTree(node, list);
            }
        }
        return parentNode;
    }

}
