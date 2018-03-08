package com.jeegem.controller.exterInt.test;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.system.dict.SysDict;
import com.jeegem.service.article.ArticleService;
import com.jeegem.service.system.dict.SysDictService;

@Controller
@RequestMapping("/exterInt/test/")
public class TestController extends BaseController<Object>{
	
	@Autowired
	public SysDictService service;
	
	@Reference
	private ArticleService articleService;
	
	@RequestMapping("/getArticle")
	public String getArticle(){
		
		String res = articleService.addArticle("teswt");
		
		System.out.println("res=>"+res);
		
		return "/system/test";
	}

	
	//@RequestMapping(value="test", method=RequestMethod.POST)
	@RequestMapping(value="getDict")
	@ResponseBody
	public AjaxRes getDict(String key){
		AjaxRes ar=getAjaxRes();			
			try {
				//"nowWeather"
				SysDict sysDict=new SysDict();
				if(StringUtils.isNotBlank(key)){
					sysDict.setParamKey(key);
					List<SysDict> list=service.find(sysDict);
					if(list.size()>0){
						ar.setSucceed(list.get(0),"成功");
					}		
				}
			} catch (Exception e) {
				logger.error(e.toString(),e);
	
			}
		return ar;
	}
}
