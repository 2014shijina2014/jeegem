package com.jeegem.controller.workflow.instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.base.Const;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.workflow.online.TaskVo;
import com.jeegem.entity.workflow.process.ProcessInstanceVo;
/**
 * 运行中流程管理
 */
@Controller
@RequestMapping(value = "/backstage/workflow/instance/pro/")
public class ProInstanceController extends BaseController<Object>{

	 @Autowired
	 private RuntimeService runtimeService;
	 @Autowired
	 private TaskService taskService;
	/**
	 * 运行中流程列表
	 */
	@RequestMapping(value = "index")
	public String index(org.springframework.ui.Model model) {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/workflow/instance/pro/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}

	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ProcessInstanceVo> page,String keyWord) {
		AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/workflow/instance/pro/index"))) {
			try {
				int pageNum=page.getPageNum()-1;
				int pageSize=page.getPageSize();
				ProcessInstanceQuery query=runtimeService.createProcessInstanceQuery();
				List<ProcessInstance> list =new ArrayList<ProcessInstance>();
				List<ProcessInstanceVo> vos =new ArrayList<ProcessInstanceVo>();
				if(StringUtils.isNotBlank(keyWord)){
					list = query.processInstanceTenantIdLike("%"+keyWord+"%")
							.orderByProcessInstanceId().desc()
							.listPage(pageNum, pageSize);
				}else{
					list = query.orderByProcessInstanceId().desc()
							.listPage(pageNum, pageSize);
				}
				for(ProcessInstance t:list){
					ProcessInstanceVo vo=new ProcessInstanceVo(t.getId(),t.getProcessInstanceId()
							,t.getProcessDefinitionId());		
					
					// 设置当前任务信息
					Task task=taskService.createTaskQuery().processInstanceId(t.getProcessInstanceId()).active().orderByTaskCreateTime().desc().singleResult();			
					TaskVo taskVo=new TaskVo(task.getId(),task.getTaskDefinitionKey(),task.getName(),task.getProcessDefinitionId()
							,task.getProcessInstanceId(),task.getPriority(),task.getCreateTime(),task.getDueDate()
							,task.getDescription(),task.getOwner(),task.getAssignee());		
					vo.setTask(taskVo);
					
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
