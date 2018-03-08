package com.jeegem.controller.task.base;

import java.io.UnsupportedEncodingException;
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
import com.jeegem.entity.task.base.ScheduleJob;
import com.jeegem.service.task.base.ScheduleJobService;
/**
 * 任务管理
 */
@Controller
@RequestMapping("/backstage/task/scheduleJob/")
public class ScheduleJobController extends BaseController<ScheduleJob>{

	 @Autowired
	 private ScheduleJobService service;
	 
	/**
	 * 任务首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/task/scheduleJob/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ScheduleJob> page, ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/task/scheduleJob/index"))) {
			try {
				Page<ScheduleJob> result = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list", result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
		
		
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				List<ScheduleJob> list = service.find(o);
				ScheduleJob obj = list.get(0);
				ar.setSucceed(obj);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
		
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
			try {
				o.setScheduleJobId(get32UUID());
				int res = service.creatScheduleJob(o);
				if (res == 1)
					ar.setSucceedMsg(Const.SAVE_SUCCEED);
				else
					ar.setFailMsg(Const.SAVE_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				int res = service.updateScheduleJob(o);
				if (res == 1)ar.setSucceedMsg(Const.UPDATE_SUCCEED);
				else         ar.setFailMsg(Const.UPDATE_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				int res = service.deleteScheduleJob(o);
				if (res == 1)
					ar.setSucceedMsg(Const.DEL_SUCCEED);
				else
					ar.setFailMsg(Const.DEL_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value = "runOnce", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes runOnce(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				int res = service.runOnce(o);
				if (res == 1)
					ar.setSucceedMsg("运行成功");
				if (res == 2)
					ar.setFailMsg("请先停用任务");
				else
					ar.setFailMsg("运行失败");
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg("运行失败");
			}
		}
		return ar;
	}
		
	@RequestMapping(value = "resumeJob", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes resumeJob(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				int res = service.resumeJob(o);
				if (res == 1)
					ar.setSucceedMsg("启动成功");
				else if (res == 2)
					ar.setFailMsg("项目已启动");
				else
					ar.setFailMsg("启动失败");
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg("启动失败");
			}
		}
		return ar;
	}
		
	@RequestMapping(value = "pauseJob", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes pauseJob(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				int res = service.pauseJob(o);
				if (res == 1)
					ar.setSucceedMsg("暂停成功");
				else if (res == 2)
					ar.setFailMsg("项目没启动，不用暂停");
				else
					ar.setFailMsg("暂停失败");
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg("暂停失败");
			}
		}
		return ar;
	}
}
