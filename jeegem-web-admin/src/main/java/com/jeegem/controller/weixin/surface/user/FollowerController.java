package com.jeegem.controller.weixin.surface.user;

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
import com.jeegem.entity.weixin.user.WxFollower;
import com.jeegem.service.weixin.user.WxFollowerService;

@Controller
@RequestMapping("/backstage/weixin/follower/")
public class FollowerController extends BaseController<WxFollower>{
	
	@Autowired
	public WxFollowerService service;
	
	@RequestMapping("index")	
	public String index(Model model) {
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));	
			return "/system/weixin/follower/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<WxFollower> page,WxFollower o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/weixin/follower/index"))){
			try {
				Page<WxFollower> result=service.findByPage(o,page);
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",result);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(WxFollower o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				List<WxFollower> list= service.find(o);
				WxFollower obj=list.get(0);
				ar.setSucceed(obj);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	
	@RequestMapping(value="syncFollower", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes syncFollower(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){
			try {
				service.syncFollower();
				ar.setSucceedMsg("同步成功");
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg("同步失败");
			}
		}
		return ar;
	}
}
