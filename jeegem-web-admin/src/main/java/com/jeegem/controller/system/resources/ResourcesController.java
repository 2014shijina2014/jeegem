package com.jeegem.controller.system.resources;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.tree.MenuTreeUtil;
import com.jeegem.common.utils.tree.entity.ZNodes;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.system.resources.Resources;
import com.jeegem.service.system.resources.ResourcesService;
/*
 * 菜单
 */
@Controller
@RequestMapping("/backstage/resources/")
public class ResourcesController extends BaseController<Resources>{
	
	@Autowired
	public ResourcesService service;
	/**
	 * 菜单首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/system/resources/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="listAllParentMenu", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes listAllParentMenu(Resources o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/resources/index"))){
			try {
				List<Resources> r=service.find(o);
				List<Resources> tree=MenuTreeUtil.buildTree(r);
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
	
	@RequestMapping(value="listResources", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes listResources(Resources o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/resources/index"))){
			try {
				List<ZNodes> r=service.listResources(o);
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
		
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(Resources o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){	
			try {
				o.setId(get32UUID());
				o.setCreateTime(new Date());
				o.setResUrl(StringUtils.trim(o.getResUrl()));
				o.setParentId(StringUtils.isNotBlank(o.getParentId())?o.getParentId():"0");
				service.insert(o);
				ar.setSucceedMsg(Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String chks){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){	
			try {
				String[] chk =chks.split(",");
				List<Resources> list=new ArrayList<Resources>();
				for(String s:chk){
					Resources sd=new Resources();
					sd.setId(s);
					list.add(sd);
				}
				int res=service.tranDeleteBatch(list);
				if(res==1)ar.setSucceedMsg(Const.DEL_SUCCEED);
				else     ar.setFailMsg("请先删除子资源");

			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(Resources o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				List<Resources> list=service.find(o);
				Resources resources=list.get(0);
				ar.setSucceed(resources);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(Resources o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				int res=service.updateMenu(o);
				if(res==1)      ar.setSucceedMsg(Const.UPDATE_SUCCEED);
				else if(res==2) ar.setFailMsg("上级资源不能是本资源");
				else            ar.setFailMsg(Const.UPDATE_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(Resources o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				int res=service.tranDelete(o);
				if(res==1)ar.setSucceedMsg(Const.DEL_SUCCEED);
				else      ar.setFailMsg("请先删除子资源");
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}	
		return ar;
	}
}
