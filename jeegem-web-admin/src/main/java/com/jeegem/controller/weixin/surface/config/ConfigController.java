package com.jeegem.controller.weixin.surface.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.weixin.util.ConfigReader;
import com.jeegem.common.utils.weixin.vo.MPAccount;
import com.jeegem.controller.base.BaseController;

@Controller
@RequestMapping("/backstage/weixin/config/")
public class ConfigController extends BaseController<Object>{
	
	@RequestMapping("index")	
	public String index(Model model) {
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			//model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));	
			return "/system/weixin/config/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="getConfig", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getConfig(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/weixin/config/index"))){
			try {
				MPAccount mc=new MPAccount();
				ConfigReader _cr = new ConfigReader(Const.WEIXIN_CONFIG);
				mc.setAppId(_cr.get("appId"));
				mc.setAppSecret(_cr.get("appSecret"));
				mc.setToken(_cr.get("token"));
				mc.setAESKey(_cr.get("aseKey"));
				mc.setTuring(_cr.getBoolean("turing"));	
				ar.setSucceed(mc);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
}
