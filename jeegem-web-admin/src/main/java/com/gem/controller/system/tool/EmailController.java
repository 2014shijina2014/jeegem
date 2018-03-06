package com.gem.controller.system.tool;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gem.common.ajax.AjaxRes;
import com.gem.common.mybatis.Page;
import com.gem.common.utils.base.Const;
import com.gem.common.utils.email.MailConfig;
import com.gem.common.utils.email.MailUtil;
import com.gem.controller.base.BaseController;
import com.gem.entity.system.tool.Email;
import com.gem.service.system.tool.EmailService;

@Controller
@RequestMapping("/backstage/tool/email/")
public class EmailController extends BaseController<Email>{

	@Autowired
	private EmailService service;
	
	@RequestMapping("index")	
	public String index(Model model) throws UnsupportedEncodingException{
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			return "/system/tool/email/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<Email> page,Email o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/tool/email/index"))){
			try {
				Page<Email> em=service.findByPage(o, page);
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",em);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}

	@RequestMapping(value="sendMail", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes sendMail(Email o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/tool/email/index"))){
			try {
				o.setId(get32UUID());
				boolean res=service.sentEmailSimple(o);
				if(res){
					ar.setSucceedMsg("发送成功");
				}
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg("发送失败");
			}
		}	
		return ar;
	}
			
	@RequestMapping(value="getConfig", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getConfig(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/tool/email/index"))){
			try {
				MailConfig mc=MailUtil.setConfig(Const.EMAIL_CONFIG);
				ar.setSucceed(mc);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}		
}
