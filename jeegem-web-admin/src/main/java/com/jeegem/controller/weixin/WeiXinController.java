package com.jeegem.controller.weixin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.weixin.core.WechatDefHandler;
import com.jeegem.common.utils.weixin.core.WechatKernel;
import com.jeegem.common.utils.weixin.util.ConfigReader;
import com.jeegem.common.utils.weixin.vo.MPAccount;
/** 
 * 微信平台
 * @version
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController {

	private static final ConfigReader _cr = new ConfigReader(Const.WEIXIN_CONFIG);

	protected static WechatKernel _wk = new WechatKernel();
	
	//@PostConstruct
	public void init() {
        MPAccount mpact = new MPAccount();
        // 修改为实际的公众号信息,可以在开发者栏目中查看     
        mpact.setAppId(_cr.get("appId"));
        mpact.setAppSecret(_cr.get("appSecret"));
        mpact.setToken(_cr.get("token"));
        mpact.setAESKey(_cr.get("aseKey"));
        mpact.setTuring(_cr.getBoolean("turing"));
        _wk.setMpAct(mpact);
        _wk.setWechatHandler(new WechatDefHandler());
    }
	/**
     * 与微信服务器互动
     * @param req   微信服务器请求
     * @param resp  响应微信服务器
     * @throws IOException
     */
	@RequestMapping(value="/index")
	public void interact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        init();
        _wk.setParams(req.getParameterMap());
        String respmsg = "success";
        if ("GET".equals(req.getMethod())) {
            respmsg = _wk.check();
        }else {
            respmsg = _wk.handle(req.getInputStream());
        }
        // 输出回复消息
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.getWriter().print(respmsg);
    }
	
}
