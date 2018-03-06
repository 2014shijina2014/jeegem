package com.gem.controller.exterInt.test;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gem.common.ajax.AjaxRes;
import com.gem.controller.base.BaseController;
import com.gem.entity.system.dict.SysDict;
import com.gem.service.system.dict.SysDictService;

@Controller
@RequestMapping("/exterInt/test/")
public class TestController extends BaseController<Object>{
	
	@Autowired
	public SysDictService service;
	
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
