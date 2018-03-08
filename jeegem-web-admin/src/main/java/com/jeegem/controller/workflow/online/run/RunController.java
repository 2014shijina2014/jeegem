package com.jeegem.controller.workflow.online.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.security.AccountShiroUtil;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.workflow.online.TaskVo;
import com.jeegem.entity.workflow.process.ProcessInstanceVo;
/**
 * 在线运行中任务
 */
@Controller
@RequestMapping(value = "/backstage/workflow/online/run/")
public class RunController extends BaseController<Object>{

	private static final String SECURITY_URL="/backstage/workflow/online/run/index";
	
	 @Autowired
	 private RuntimeService runtimeService;
	 @Autowired
	 private TaskService taskService;
    /**
     * 任务列表
     */
    @RequestMapping(value = "index")
    public String index(org.springframework.ui.Model model) {
    	if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){	
    		model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/workflow/online/run/list";
		}
		return Const.NO_AUTHORIZED_URL;
    }
 
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ProcessInstanceVo> page,String keyWord) {
    	AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				int pageNum=page.getPageNum()-1;
				int pageSize=page.getPageSize();
				List<ProcessInstanceVo> vos=new ArrayList<ProcessInstanceVo>();
				String currentUserId = AccountShiroUtil.getCurrentUser().getAccountId();			
				// 根据当前人的ID查询
				ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().involvedUser(currentUserId).active().orderByProcessInstanceId().desc();
				List<ProcessInstance> list=query.listPage(pageNum, pageSize);
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
