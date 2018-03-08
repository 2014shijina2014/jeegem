package com.jeegem.common.utils.tree;

import java.util.ArrayList;
import java.util.List;

import com.jeegem.entity.weixin.menu.WxMenu;

public class WxMenuTreeUtil {
	   
    private static List<WxMenu> getChildren(List<WxMenu> menusDB,WxMenu node){  
        List<WxMenu> children = new ArrayList<WxMenu>();  
        String id = node.getId();  
        for (WxMenu child : menusDB) {  
            if (id.equals(child.getpId())) {  
                children.add(child);               
            }  
        }  
        return children;  
    }  
    
	   
	/**
	 * 建立树菜单
	 * @param menusDB 菜单集合（不是树）
	 * @return 有样式的树的菜单集合
	 */
    public static List<WxMenu> buildTree(List<WxMenu> menusDB){  
    	List<WxMenu> res=new ArrayList<WxMenu>();
    	  for (WxMenu node:menusDB) { 
    		  if ("0".equals(node.getpId())) { 
    			  List<WxMenu> children = getChildren(menusDB,node);  
    			  node.setNodes(children);
    			  build(menusDB,node,res); 
    			  res.add(node);
    		  }    		
    	  }
    	return res;
    }  
    
    private static void build(List<WxMenu> menusDB,WxMenu node,List<WxMenu> res){  
        List<WxMenu> children = getChildren(menusDB,node); 
        if (!children.isEmpty()) {  
            for (WxMenu child : children) {  
            	List<WxMenu> children2 = getChildren(menusDB,child);
            	child.setNodes(children2);
                build(menusDB,child,res);  
            } 
        }   
    } 
    
    
}
