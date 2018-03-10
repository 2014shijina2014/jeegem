package com.jeegem.controller.system.account;

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
import com.jeegem.service.system.account.AccountService;
/**
 * 
 * <p>
 * Title: AccountController.java
 * </p>
 * 
 * <p>
 * Description: 
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * <p>
 * Company: www.jeegem.com
 * </p>
 * 
 * @author JeeGem
 * 
 * @date 2018年3月11日 上午12:02:45
 * 
 * @version JeeGem V3.0
 */
@Controller
@RequestMapping("/backstage/account/")
public class AccountController extends BaseController<Account>{

	@Autowired
	private AccountService service;

	@RequestMapping("index")	
	public String index(Model model){
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));	
			return "/system/account/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="roleTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes roleTree(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/account/index"))){
			try {
				List<ZNodes> list=service.getRoles();
				ar.setSucceed(list);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
		
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<Account> page,Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/account/index"))){
			try {
				Page<Account> accounts=service.findByPage(o, page);
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",accounts);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){			
			try {
				o.setAccountId(get32UUID());	
				int res=service.insertAccount(o);
				if(res==1)ar.setSucceedMsg(Const.SAVE_SUCCEED);
				else ar.setFailMsg("登录名已存在");	
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
				service.deleteBatchAccount(chks);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				List<Account> list=service.find(o);
				Account acount =list.get(0);
				ar.setSucceed(acount);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
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
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
			try {
				service.deleteAccount(o);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="resetPwd", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes resetPwd(Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
			try {		
				o.setPassword(getPageData().getString("pwd"));
				int res=service.sysResetPwd(o);
				if(res==1) ar.setSucceedMsg(Const.UPDATE_SUCCEED);
				else ar.setFailMsg(Const.UPDATE_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="setSetting", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes setSetting(String skin){
		AjaxRes ar=getAjaxRes();
		try {
			service.setSetting(skin);
			ar.setSucceedMsg(Const.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.UPDATE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="getPerData", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPerData(){
		AjaxRes ar=getAjaxRes();
		try {
			Account account=service.getPerData();
			ar.setSucceed(account);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="setHeadpic", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes setHeadpic(Account o){
		AjaxRes ar=getAjaxRes();
		try {
			service.setHeadpic(o);
			ar.setSucceedMsg(Const.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.UPDATE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="setPerData", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes setPerData(Account o){
		AjaxRes ar=getAjaxRes();
		try {
			service.setPerData(o);
			ar.setSucceedMsg(Const.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.UPDATE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="preResetPWD", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes resetPWD(String opwd,String npwd,String qpwd){
		AjaxRes ar=getAjaxRes();
		try {
			int res=service.preResetPwd(opwd,npwd,qpwd);
			if     (res==1)ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			else if(res==2)ar.setFailMsg("密码不正确");			
			else if(res==3)ar.setFailMsg("两次密码不一致");			
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.UPDATE_FAIL);
		}
		return ar;
	}
	
}
