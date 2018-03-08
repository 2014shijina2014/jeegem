package com.jeegem.controller.system.tool;

import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.twoCode.TwoDimensionCode;
import com.jeegem.controller.base.BaseController;
@Controller
@RequestMapping("/backstage/tool/qrCode/")
public class QRCodeController extends BaseController<Object>{

	@RequestMapping("index")	
	public String index(Model model){
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			return "/system/tool/qrCode/list";
		}
		return Const.NO_AUTHORIZED_URL;	
	}
	
	@RequestMapping(value = "upload2Code")
	@ResponseBody
	public AjaxRes upload2Code(@RequestParam(value = "codeFile", required = false) MultipartFile file) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/tool/qrCode/index"))) {
			try {
				// String fileName = file.getOriginalFilename();
				InputStream fileInputStream = file.getInputStream();
				String content = TwoDimensionCode.decoderQRCode(fileInputStream);
				ar.setSucceed(content, "解析成功");
			} catch (Exception e) {
				logger.error("二维码解析失败", e);
				ar.setFailMsg("解析失败");
			}
		}
		return ar;
	}
}
