package com.jeegem.controller.system.org;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.tree.entity.ZNodes;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.system.account.Account;
import com.jeegem.entity.system.org.Position;
import com.jeegem.service.system.org.PositionService;
/*
 * 职务管理
 */
@Controller
@RequestMapping("/backstage/org/position/")
public class PositionController extends BaseController<Position>{

	@Autowired
	private PositionService service;
	
	private static final String SECURITY_URL="/backstage/org/position/index";
	/**
	 * 职务管理首页
	 */
	@RequestMapping("index")
	public String index(Model model) {	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/system/org/position/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	
	@RequestMapping(value="getOrgAndPositionTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getOrgAndPositionTree(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				List<ZNodes> r=service.getOrgAndPositionTree();
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="getPreOrgTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPreOrgTree(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				List<ZNodes> r=service.getPreOrgTree();
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="addPos", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes addPos(Position o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				o.setId(get32UUID());
				o.setCreateTime(new Date());
				service.insert(o);
				ar.setSucceedMsg(Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="findOrgByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findOrgByPage(Page<Position> page,Position o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){
			try {
				Page<Position> positions=service.findAllPosByPage(o, page);
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("list",positions);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="findPos", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findPos(Position o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){		
			try {
				List<Position> list=service.find(o);
				Position position=list.get(0);
				ar.setSucceed(position);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="updatePos", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes updatePos(Position o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				o.setUpdateTime(new Date());
				service.update(o);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="delPos", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delPos(Position o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				service.deletePos(o);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}	
		return ar;
	}

	@RequestMapping(value="findArrangeAccByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findArrangeAccByPage(Page<Account> page,Position o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){
			try {
				Page<Account> accs=service.findArrangeAccByPage(o, page);
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",accs);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="arrangeAcc", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes arrangeAcc(String posId,String chks){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				service.saveAccountPosition(posId,chks);
				ar.setSucceedMsg(Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="findPosByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findPosByPage(Page<Account> page,Position o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){
			try {
				o.setId(getPageData().getString("posId"));
				Page<Account> aps=service.findPosByPage(o, page);
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",aps);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="delAccPos", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delAccPos(String accId){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				service.deleteAccPosByAccId(accId);
				ar.setSucceedMsg("移除成功");
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg("移除失败");
			}
		}	
		return ar;
	}
}
