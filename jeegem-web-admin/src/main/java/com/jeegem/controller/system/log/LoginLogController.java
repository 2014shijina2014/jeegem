package com.jeegem.controller.system.log;

import java.util.ArrayList;
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
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.system.log.LoginLog;
import com.jeegem.service.system.log.LoginLogService;
/*
 * 登录日志
 */
@Controller
@RequestMapping("/backstage/loginLog/")
public class LoginLogController extends BaseController<LoginLog>{
	
	@Autowired
	public LoginLogService service;
	/**
	 * 登录日志首页
	 */
	@RequestMapping("index")
	public String index(Model model) {	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){	
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));	
			return "/system/log/loginLog/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<LoginLog> page,LoginLog o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/loginLog/index"))){
			try {
				Page<LoginLog> result=service.findByPage(o,page);
				Map<String, Object> p=new HashMap<String, Object>();
				//p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",result);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
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
				List<LoginLog> list=new ArrayList<LoginLog>();
				for(String s:chk){
					LoginLog sd=new LoginLog();
					sd.setId(s);
					list.add(sd);
				}
				service.deleteBatch(list);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}	
		return ar;
	}
}
