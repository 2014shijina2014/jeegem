package com.jeegem.common.utils.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jeegem.entity.system.resources.Resources;

public class MenuTreeUtil {
	   
	/**
	 * 建立树菜单
	 * @param menusDB 菜单集合（不是树）
	 * @return 有样式的树的html字符串
	 */
    public static String buildTreeHtml(List<Resources> menusDB){  
    	StringBuffer html = new StringBuffer();
        for (int i=0;i<menusDB.size();i++) { 
        	Resources node=menusDB.get(i);
            if ("0".equals(node.getParentId())) { 
            	boolean childrenHas=false;
            	List<Resources> children = getChildren(menusDB,node);  
            	if(!children.isEmpty())childrenHas=true;
            	if(i==0){
            		html.append("\r\n<li id='menu"+node.getId()+"' class='active' >"); 
            	}else{
            		html.append("\r\n<li id='menu"+node.getId()+"' >"); 
            	}           	
            	html.append("\r\n<a ");
            	html.append(" href='#maincontent' style='cursor:pointer;'  onclick=\"openMenu('"+node.getType()+"','menu"+node.getId()+"','menu0','"+node.getName()+"','"+(StringUtils.isNotBlank(node.getResUrl())?node.getResUrl().trim():"noset")+"')\" ");       		    	
            	if(childrenHas)html.append(" target='mainFrame' class='dropdown-toggle' "); 
            	html.append(" >"); 
            	if(StringUtils.isNotEmpty(node.getIcon()))html.append("\r\n<i class= "+ node.getIcon()+" ></i>"); 
            	html.append("\r\n<span class='menu-text'>" + node.getName()+ "</span>"); 
            	if(childrenHas)html.append("<b class='arrow icon-angle-down'></b>");
            	html.append("</a>");
                build(menusDB,node,html);  
                html.append("</li>");
            }  
        }  
        return html.toString();  
    }  
      
    private static void build(List<Resources> menusDB,Resources node,StringBuffer html){  
        List<Resources> children = getChildren(menusDB,node); 
        if (!children.isEmpty()) {  
            html.append("\r\n<ul class='submenu'>");
            for (Resources child : children) {  
            	boolean childrenHas=false;
            	List<Resources> children2 = getChildren(menusDB,child);
            	if(!children2.isEmpty())childrenHas=true;
            	html.append("\r\n<li id='menu"+child.getId()+"' >"); 
            	html.append("\r\n<a ");
        		html.append(" href='#maincontent' style='cursor:pointer;' onclick=\"openMenu('"+child.getType()+"','menu"+child.getId()+"','menu"+child.getParentId()+"','"+child.getName()+"','"+(StringUtils.isNotBlank(child.getResUrl())?child.getResUrl().trim():"noset")+"')\" ");
            	if(childrenHas)html.append(" target='mainFrame' class='dropdown-toggle' "); 
            	html.append(" >"); 
            	if(!"0".equals(child.getParentId()))html.append("\r\n<i class='icon-double-angle-right' ></i>"); 
            	html.append("\r\n<span class='menu-text'>");
            	if(StringUtils.isNotEmpty(child.getIcon())){
            		html.append("\r\n<i class= "+ child.getIcon()+" ></i>&nbsp;&nbsp;"+child.getName());
            	}else{
            		html.append(child.getName()); 
            	}
            	html.append("</span>"); 
            	if(childrenHas)html.append("<b class='arrow icon-angle-down'></b>");
            	html.append("</a>");
                build(menusDB,child,html);  
                html.append("</li>");  
            } 
            html.append("\r\n</ul>");
        }   
    }  
      
    private static List<Resources> getChildren(List<Resources> menusDB,Resources node){  
        List<Resources> children = new ArrayList<Resources>();  
        String id = node.getId();  
        for (Resources child : menusDB) {  
            if (id.equals(child.getParentId())) {  
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
    public static List<Resources> buildTree(List<Resources> menusDB){  
    	List<Resources> res=new ArrayList<Resources>();
    	  for (Resources node:menusDB) { 
    		  if ("0".equals(node.getParentId())) { 
    			  List<Resources> children = getChildren(menusDB,node);  
    			  node.setNodes(children);
    			  build(menusDB,node,res); 
    			  res.add(node);
    		  }    		
    	  }
    	return res;
    }  
    
    private static void build(List<Resources> menusDB,Resources node,List<Resources> res){  
        List<Resources> children = getChildren(menusDB,node); 
        if (!children.isEmpty()) {  
            for (Resources child : children) {  
            	List<Resources> children2 = getChildren(menusDB,child);
            	child.setNodes(children2);
                build(menusDB,child,res);  
            } 
        }   
    } 
    
    
}
