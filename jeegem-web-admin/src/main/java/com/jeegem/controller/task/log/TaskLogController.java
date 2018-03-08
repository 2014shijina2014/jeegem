package com.jeegem.controller.task.log;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.DateUtils;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.poi.ObjectExcelView;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.task.log.TaskLog;
import com.jeegem.service.task.log.TaskLogService;

@Controller
@RequestMapping("/backstage/task/taskLog/")
public class TaskLogController extends BaseController<TaskLog>{

	 @Autowired
	 private TaskLogService service;

	/**
	 * 任务首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/task/log/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
		
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<TaskLog> page, TaskLog o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/task/taskLog/index"))) {
			try {
				Page<TaskLog> result = service.findByPage(o, page);
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
		
	@RequestMapping(value = "delBatch", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String chks) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
			try {
				String[] chk = chks.split(",");
				List<TaskLog> list = new ArrayList<TaskLog>();
				for (String s : chk) {
					TaskLog sd = new TaskLog();
					sd.setId(s);
					list.add(sd);
				}
				service.deleteBatch(list);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "exportXls")
	public ModelAndView exportXls(TaskLog taskLog) {
		ModelAndView mv = this.getModelAndView();
		if (doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)) {
			try {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("fileName", "TaskLog");// 设置文件名

				List<String> titles = new ArrayList<String>();
				titles.add("任务对象"); // 1
				titles.add("任务类"); // 2
				titles.add("日志类型"); // 3
				titles.add("任务类描述"); // 4
				titles.add("记录时间"); // 5
				model.put("titles", titles);// 设置标题们

				List<TaskLog> taskLogs = service.find(taskLog);
				List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
				for (TaskLog t : taskLogs) {
					Map<String, String> vpd = new HashMap<String, String>();
					vpd.put("var1", t.getName()); // 1
					vpd.put("var2", t.getClassName()); // 2
					vpd.put("var3", (t.getType() == 1) ? "正常" : "异常"); // 3
					vpd.put("var4", t.getDescription()); // 4
					vpd.put("var5", DateUtils.formatDate(t.getCreateTime(), DateUtils.parsePatterns[3])); // 5
					varList.add(vpd);
				}
				model.put("varList", varList);// 设置内容们
				ObjectExcelView erv = new ObjectExcelView(); // 执行excel操作
				mv = new ModelAndView(erv, model);
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
		} else {
			mv.setViewName(Const.NO_AUTHORIZED_URL);
		}
		return mv;
	}
}
