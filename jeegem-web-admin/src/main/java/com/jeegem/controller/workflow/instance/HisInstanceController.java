package com.jeegem.controller.workflow.instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.base.Const;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.workflow.process.HistoricProcessInstanceVo;
/**
 * 历史流程管理
 */
@Controller
@RequestMapping(value = "/backstage/workflow/instance/his/")
public class HisInstanceController extends BaseController<Object>{

	 @Autowired
	 private HistoryService historyService;
	 
	/**
	 * 历史流程列表
	 */
	@RequestMapping(value = "index")
	public String index(org.springframework.ui.Model model) {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/workflow/instance/his/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}

	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<HistoricProcessInstanceVo> page,String keyWord) {
		AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/workflow/instance/his/index"))) {
			try {
				int pageNum=page.getPageNum()-1;
				int pageSize=page.getPageSize();
				HistoricProcessInstanceQuery query=historyService.createHistoricProcessInstanceQuery().finished()
						.orderByProcessInstanceEndTime().desc();
				List<HistoricProcessInstance> list = query.listPage(pageNum, pageSize);
				List<HistoricProcessInstanceVo> vos=new ArrayList<HistoricProcessInstanceVo>();
				for (HistoricProcessInstance t : list) {
					HistoricProcessInstanceVo vo = new HistoricProcessInstanceVo(t.getId(), t.getProcessDefinitionId(),
							t.getStartTime(), t.getEndTime(), t.getDeleteReason());
					vos.add(vo);
				}
				long count=query.count();
				page.setTotalRecord((int) count);
				page.setResults(vos);
				
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",page);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
}
