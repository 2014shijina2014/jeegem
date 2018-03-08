package com.jeegem.controller.weixin.surface.menu;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.tree.WxMenuTreeUtil;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.weixin.menu.WxMenu;
import com.jeegem.service.weixin.menu.WxMenuService;

@Controller
@RequestMapping("/backstage/weixin/menu/")
public class MenuController extends BaseController<WxMenu>{
	
	@Autowired
	public WxMenuService service;
	
	private static final String SECURITY_URL="/backstage/weixin/menu/index";
	
	@RequestMapping("index")	
	public String index(Model model) {
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));	
			return "/system/weixin/menu/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="menuTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes menuTree(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){
			try {
				WxMenu o=new WxMenu();
				List<WxMenu> r=service.find(o);
				List<WxMenu> tree=WxMenuTreeUtil.buildTree(r);
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",tree);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	@RequestMapping(value="findMenu", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findMenu(WxMenu o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){
			try {
				List<WxMenu> list=service.find(o);
				WxMenu wxMenu=list.get(0);
				ar.setSucceed(wxMenu);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="addMenu", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes addMenu(@RequestBody WxMenu o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){		
			try {
				int res=service.insertMenu(o);
				if(res==1)     ar.setSucceedMsg(Const.SAVE_SUCCEED);
				else if(res==2)	ar.setFailMsg("最多包括3个一级菜单");
				else if(res==3) ar.setFailMsg("最多包含5个二级菜单");
				else if(res==4) ar.setFailMsg("最多包含10个图文信息");
				else ar.setFailMsg(Const.SAVE_FAIL);				
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);				
			}
		}
		return ar;
	}
	
	@RequestMapping(value="updateMenu", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateMenu(@RequestBody WxMenu o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){
			try {
				o.setUpdateTime(new Date());
				int res=service.updateMenu(o);
				if(res==1) ar.setSucceedMsg(Const.UPDATE_SUCCEED);
				else if(res==4) ar.setFailMsg("最多包含10个图文信息");
				else ar.setFailMsg(Const.UPDATE_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="delMenu", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delMenu(WxMenu o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){
			try {
				int res=service.deleteMenu(o);
				if(res==1)      ar.setSucceedMsg(Const.DEL_SUCCEED);
				else if(res==2) ar.setFailMsg("请先删除子菜单");
				else            ar.setFailMsg(Const.DEL_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="syncMenu", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes syncMenu(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){
			try {
				int res=service.syncMenu();
				if(res==1)      ar.setSucceedMsg(Const.UPDATE_SUCCEED);
				else if(res==2) ar.setFailMsg("目前没有菜单");
				else          	ar.setFailMsg(Const.UPDATE_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}	
		return ar;
	}
}
