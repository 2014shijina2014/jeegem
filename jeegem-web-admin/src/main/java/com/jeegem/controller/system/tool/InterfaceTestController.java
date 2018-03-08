package com.jeegem.controller.system.tool;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.utils.HttpUtil;
import com.jeegem.common.utils.base.Const;
import com.jeegem.controller.base.BaseController;

@Controller
@RequestMapping("/backstage/tool/interfaceTest/")
public class InterfaceTestController extends BaseController<Object>{


	@RequestMapping("index")	
	public String index(Model model){
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			return "/system/tool/interfaceTest/list";
		}
		return Const.NO_AUTHORIZED_URL;	
	}
	
	
	/**
	 *	接口内部请求
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value="/severTest",method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes severTest(String reqMethod,String serverUrl,String params){
		AjaxRes ar = getAjaxRes();
		Map<String, Object> p=new HashMap<String, Object>();
		String str = "",rTime="";
		try{
			boolean https=true;
			if(serverUrl.contains("https:"))https=true;
			else                           https=false;	
			long startTime = System.currentTimeMillis(); 					//请求起始时间_毫秒
			if("POST".equals(reqMethod)){
				str=HttpUtil.post(serverUrl,params,https);
			}else{
				str=HttpUtil.get(serverUrl,https);		
			}			
			long endTime = System.currentTimeMillis(); 						//请求结束时间_毫秒		
			rTime = String.valueOf(endTime - startTime); 
			p.put("result", str);			//返回结果
			p.put("reqTime", rTime);		//服务器请求时间 毫秒
			ar.setSucceed(p, "请求成功");
		}catch(Exception e){
			logger.error(e.toString(),e);
			ar.setFailMsg("请求失败");
		}
		return ar;
	}
}
