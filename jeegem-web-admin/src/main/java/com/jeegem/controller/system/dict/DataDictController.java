package com.jeegem.controller.system.dict;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.base.Const;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.system.dict.DataDict;
import com.jeegem.service.system.dict.DataDictService;
/*
 * 数据字典
 */
@Controller
@RequestMapping("/backstage/dataDict/")
public class DataDictController extends BaseController<DataDict>{
	
	@Autowired
	public DataDictService service;

	@RequestMapping("index")
	public String index(Model model) {	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/dict/data/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<DataDict> page,DataDict o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/dataDict/index"))){
			try {
				Page<DataDict> result=service.findByPage(o, page);
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
	public AjaxRes find(DataDict o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				List<DataDict> list= service.find(o);
				DataDict obj=list.get(0);
				ar.setSucceed(obj);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(@RequestBody DataDict o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){					
			try {
				o.setId(get32UUID());
				int res=service.insertDataDict(o);
				if(res==1)     ar.setSucceedMsg(Const.SAVE_SUCCEED);
				else if(res==0)ar.setFailMsg("关键字已存在");		
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(@RequestBody DataDict o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){		
			try {
				o.setUpdateTime(new Date());
				int res=service.updateDataDict(o);
				if(res==1) ar.setSucceedMsg(Const.UPDATE_SUCCEED);
				else ar.setFailMsg(Const.UPDATE_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
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
				List<DataDict> list=new ArrayList<DataDict>();
				for(String s:chk){
					DataDict sd=new DataDict();
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
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(DataDict o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){			
			try {
				service.delete(o);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}		
		return ar;
	}
	
	@RequestMapping(value="getDictSelect", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getDictSelect(String ids,String keys){	
		AjaxRes ar=getAjaxRes();	
		try {
			Map<String,DataDict> obj=service.findDatas(ids,keys);
			ar.setSucceed(obj);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.DATA_FAIL);
		}
		return ar;
	}	
}
