package com.jfsoft.vo;

import java.util.ArrayList;
import java.util.List;

public class NodeTree {
	
	private Object node;
	
	//此节点下的子节点list，可以无限制 地向下扩展子节点
    private List<NodeTree> list;
    
    public NodeTree(){
        this.list = new ArrayList<NodeTree>();
    }
    
    public NodeTree(Object node){
    	this.node=node;
    	this.list = new ArrayList<NodeTree>();
    }
    
    //为树添加节点
    public void add(NodeTree node){
        this.list.add(node);
    }
    
    //以下为所有属性的set、get方法
    public List<NodeTree> getList() {
        return list;
    }

    public void setList(List<NodeTree> list) {
        this.list = list;
    }

	public Object getNode() {
		return node;
	}

	public void setNode(Object node) {
		this.node = node;
	}

}
