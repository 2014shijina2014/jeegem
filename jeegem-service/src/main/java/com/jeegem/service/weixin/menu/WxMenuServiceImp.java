package com.jeegem.service.weixin.menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeegem.common.utils.base.UuidUtil;
import com.jeegem.common.utils.tree.WxMenuTreeUtil;
import com.jeegem.common.utils.weixin.vo.api.Menu;
import com.jeegem.entity.weixin.event.WxEventClick;
import com.jeegem.entity.weixin.menu.WxMenu;
import com.jeegem.repository.weixin.event.WxEventClickDao;
import com.jeegem.repository.weixin.menu.WxMenuDao;
import com.jeegem.service.weixin.base.WxBaseServiceImp;
@Service("WxMenuService")
public class WxMenuServiceImp extends WxBaseServiceImp<WxMenu> implements WxMenuService {

	@Autowired
	private WxEventClickDao wxEventClickDao;
	
	@Override@Transactional
	public synchronized int insertMenu(WxMenu wxMenu) {
		int res=0;
		WxMenuDao dao=(WxMenuDao)baseDao;
		Date d=new Date();
		int menuCount=dao.menuCount(wxMenu);
		if("0".equals(wxMenu.getpId())){
			if(menuCount>2)return 2;//超过菜单数量，目前只能为3个
		}else{
			if(menuCount>4)return 3;//超过子菜单数量，目前只能为5个
		}
		wxMenu.setId(UuidUtil.get32UUID());
		wxMenu.setCreateTime(d);
		String type=wxMenu.getType();
		if(StringUtils.isNotBlank(type)){
			if("click".equals(type)){//如果是click，先插入点击事件数据
				String selectType=wxMenu.getSelectType();		
				List<WxEventClick> items=wxMenu.getItems();
				if(items!=null&&items.size()>0){
					if(items.size()>10)return 4;//图文超过数量，目前只能为10个
					if("text".equals(selectType)){
						String key=UuidUtil.get32UUID();
						wxMenu.setKeyId(key);
						WxEventClick text=items.get(0);
						text.setId(UuidUtil.get32UUID());
						text.setKeyId(key);
						text.setCreateTime(d);
						text.setSort(1);
						wxEventClickDao.insert(text);
					}else if("imageText".equals(selectType)){
						String keyId=UuidUtil.get32UUID();
						wxMenu.setKeyId(keyId);
						for(WxEventClick i:items){
							i.setId(UuidUtil.get32UUID());
							i.setKeyId(keyId);
							i.setCreateTime(d);
						}
						wxEventClickDao.insertItems(items);
					}else if("image".equals(selectType)){
						String key=UuidUtil.get32UUID();
						wxMenu.setKeyId(key);
						WxEventClick image=items.get(0);
						image.setId(UuidUtil.get32UUID());
						image.setKeyId(key);
						image.setCreateTime(d);
						image.setSort(1);
						wxEventClickDao.insert(image);
					}
				}			
			}
		}
		dao.insert(wxMenu);		
		res=1;
		return res;
	}

	@Override@Transactional
	public synchronized int updateMenu(WxMenu wxMenu) {
		int res=0;
		WxMenuDao dao=(WxMenuDao)baseDao;
		Date d=new Date();
		wxMenu.setUpdateTime(d);			
		String type=wxMenu.getType();	
		String keyId=wxMenu.getKeyId();			
		if(StringUtils.isNotBlank(type)){	
			List<WxEventClick> items=wxMenu.getItems();
			if(items.size()>10)return 4;//图文超过数量，目前只能为10个
			//先删除对应的值				
			if(StringUtils.isBlank(keyId)){
				//keyId如果为空需要新建
				keyId=UuidUtil.get32UUID();
			}else{
				//keyId如果不为为空需要删除之前的点击事件
				WxEventClick delo=new WxEventClick();
				delo.setKeyId(keyId);
				wxEventClickDao.delete(delo);
			}	
			if("click".equals(type)){//如果是click	
				if(items!=null&&items.size()>0){
					//再进行插入数据
					String selectType=wxMenu.getSelectType();				
					if("text".equals(selectType)){					
						wxMenu.setKeyId(keyId);
						WxEventClick text=items.get(0);
						text.setId(UuidUtil.get32UUID());
						text.setKeyId(keyId);
						text.setCreateTime(d);
						text.setSort(1);
						wxEventClickDao.insert(text);
					}else if("imageText".equals(selectType)){
						wxMenu.setKeyId(keyId);
						for(WxEventClick i:items){
							i.setId(UuidUtil.get32UUID());
							i.setKeyId(keyId);
							i.setCreateTime(d);
						}
						wxEventClickDao.insertItems(items);
					}else if("image".equals(selectType)){
						wxMenu.setKeyId(keyId);
						WxEventClick image=items.get(0);
						image.setId(UuidUtil.get32UUID());
						image.setKeyId(keyId);
						image.setCreateTime(d);
						image.setSort(1);
						wxEventClickDao.insert(image);
					}
				}		
			}
			dao.update(wxMenu);
			res=1;
		}		
		return res;
	}

	@Override@Transactional
	public synchronized int deleteMenu(WxMenu wxMenu) {
		int res=0;
		WxMenuDao dao=(WxMenuDao)baseDao;
		String id=wxMenu.getId();
		if(StringUtils.isNotBlank(id)){
			WxMenu o=new WxMenu();
			o.setpId(id);
			int menuCount=dao.menuCount(o);
			if(menuCount>0)return 2;//拥有子菜单，需要先删除子菜单
		}
		WxMenu dwxMenu=dao.find(wxMenu).get(0);
		WxEventClick delo=new WxEventClick();
		delo.setKeyId(dwxMenu.getKeyId());
		wxEventClickDao.delete(delo);
		dao.delete(wxMenu);
		res=1;
		return res;
	}

	@Override
	public synchronized int syncMenu() {
		int res=0;
		WxMenuDao dao=(WxMenuDao)baseDao;
		WxMenu o=new WxMenu();
		List<WxMenu> r=dao.find(o);
		if(r.size()==0)return 2;
		List<WxMenu> tree=WxMenuTreeUtil.buildTree(r);
		Menu[] menuArray=new Menu[tree.size()];
		int i=0;
		for(WxMenu m:tree){
			List<WxMenu> nodes=m.getNodes();
			Menu menu=null;
			if(nodes!=null && nodes.size()>0){
				menu=new Menu(m.getName());
				List<Menu> subButtons =new ArrayList<Menu>();
				for(WxMenu m2:nodes){
					Menu menu2=new Menu(m2.getName());			
					 if ("view".equals(m2.getType())) {
						 menu2.setType(m2.getType());
						 menu2.setUrl(m2.getUrl());
					 }else{
						 menu2.setType(m2.getType());
						 menu2.setKey(m2.getKeyId());
					 }
					 subButtons.add(menu2);
				}
				menu.setSubButtons(subButtons);
			}else{
				 menu=new Menu(m.getName());	
				 if ("view".equals(m.getType())) {
					 menu.setType(m.getType());
					 menu.setUrl(m.getUrl());
				 }else{
					 menu.setType(m.getType());
					 menu.setKey(m.getKeyId());
				 }	
			}
			menuArray[i]=menu;
			i++;		
		}
		//接口返回结果
		boolean result=ua.createMenu(menuArray);
		if(result)res=1;
		return res;
	}

	@Override
	public WxMenu getWxMenuByKeyId(String keyId) {
		return ((WxMenuDao)baseDao).getWxMenuByKeyId(keyId);
	}

}
