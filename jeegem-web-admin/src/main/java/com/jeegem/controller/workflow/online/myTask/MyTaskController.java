package com.jeegem.controller.workflow.online.myTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.Variable;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.security.AccountShiroUtil;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.oa.leave.Leave;
import com.jeegem.entity.workflow.online.TaskVo;
import com.jeegem.service.oa.leave.LeaveService;
/**
 * 我的任务
 */
@Controller
@RequestMapping(value = "/backstage/workflow/online/myTask/")
public class MyTaskController extends BaseController<Object>{

	private static final String SIGN_SECURITY_URL="/backstage/workflow/online/myTask/signList";
	
	private static final String TODO_SECURITY_URL="/backstage/workflow/online/myTask/todoList";
	
	 @Autowired
	 private TaskService taskService; 
	 @Autowired
	 private LeaveService leaveService;
    /**
     * 签收任务列表
     */
    @RequestMapping(value = "signList")
    public String signList(org.springframework.ui.Model model) {
    	if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){	
    		model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/workflow/online/myTask/signList";
		}
		return Const.NO_AUTHORIZED_URL;
    }
 
    @RequestMapping(value = "findSignByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findSignByPage(Page<TaskVo> page,String keyWord) {
    	AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SIGN_SECURITY_URL))) {
			try {
				int pageNum=page.getPageNum()-1;
				int pageSize=page.getPageSize();
				List<TaskVo> taskVos=new ArrayList<TaskVo>();
				String currentUserId = AccountShiroUtil.getCurrentUser().getAccountId();			
				// 根据当前人的ID查询
		        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateUser(currentUserId);	        
				List<Task> tasks = taskQuery.listPage(pageNum, pageSize);
				for(Task t:tasks){
					TaskVo taskVo=new TaskVo(t.getId(),t.getTaskDefinitionKey(),t.getName(),t.getProcessDefinitionId()
							,t.getProcessInstanceId(),t.getPriority(),t.getCreateTime(),t.getDueDate()
							,t.getDescription(),t.getOwner(),t.getAssignee());					
					taskVos.add(taskVo);
				}	        
		        long count=taskQuery.count();    
				page.setTotalRecord((int) count);
				page.setResults(taskVos);				
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("list",page);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		} 
		return ar;
	}
    
    /**
     * 待办任务列表
     */
    @RequestMapping(value = "todoList")
    public String todoList(org.springframework.ui.Model model) {
    	if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){	
    		model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/workflow/online/myTask/todoList";
		}
		return Const.NO_AUTHORIZED_URL;
    }
    
    @RequestMapping(value = "findTodoByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findTodoByPage(Page<TaskVo> page,String keyWord) {
    	AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, TODO_SECURITY_URL))) {
			try {
				int pageNum=page.getPageNum()-1;
				int pageSize=page.getPageSize();
				List<TaskVo> taskVos=new ArrayList<TaskVo>();
				String currentUserId = AccountShiroUtil.getCurrentUser().getAccountId();			
				// 根据当前人的ID查询
		        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(currentUserId);	        
				List<Task> tasks = taskQuery.listPage(pageNum, pageSize);
				for(Task t:tasks){
					TaskVo taskVo=new TaskVo(t.getId(),t.getTaskDefinitionKey(),t.getName(),t.getProcessDefinitionId()
							,t.getProcessInstanceId(),t.getPriority(),t.getCreateTime(),t.getDueDate()
							,t.getDescription(),t.getOwner(),t.getAssignee());					
					taskVos.add(taskVo);
				}	        
		        long count=taskQuery.count();    
				page.setTotalRecord((int) count);
				page.setResults(taskVos);				
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("list",page);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		} 
		return ar;
	}
    /**
     * 签收任务
     */
    @RequestMapping(value = "claim/{id}", method = RequestMethod.POST)
   	@ResponseBody
   	public AjaxRes claimTask(@PathVariable("id")String taskId) {
    	AjaxRes ar=getAjaxRes();
    	if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SIGN_SECURITY_URL))) {
    		try {
				String currentUserId = AccountShiroUtil.getCurrentUser().getAccountId();
				taskService.claim(taskId, currentUserId);
				ar.setSucceedMsg("签收成功");
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg("签收失败");
			}
    	}
    	return ar;
    }
    /**
     * 办理任务
     */
    @RequestMapping(value = "complete/{id}", method = RequestMethod.POST)
   	@ResponseBody
   	public AjaxRes complete(@PathVariable("id")String taskId,Variable var) {
    	AjaxRes ar=getAjaxRes();
    	if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, TODO_SECURITY_URL))) {
    		try {
    			Map<String, Object> variables = var.getVariableMap();
				taskService.complete(taskId,variables);
				ar.setSucceedMsg("办理成功");
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg("办理失败");
			}
    	}
    	return ar;
    }
    
    
    /**
     * 查询任务
     */
    @RequestMapping(value = "findTask", method = RequestMethod.POST)
   	@ResponseBody
   	public AjaxRes findTask(String pId) {
    	AjaxRes ar=getAjaxRes();
    	if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, TODO_SECURITY_URL))) {
    		try {
    			Leave leave=leaveService.findLeaveByPId(pId);
				ar.setSucceed(leave,Const.DATA_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
    	}
    	return ar;
    }
    
    /**
     * 驳回任务
     */
    @RequestMapping(value = "reject/{id}", method = RequestMethod.POST)
   	@ResponseBody
   	public AjaxRes reject(@PathVariable("id")String taskId,String pId,String rejectReason,Variable var) {
    	AjaxRes ar=getAjaxRes();
    	if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, TODO_SECURITY_URL))) {
    		try {
    			Map<String, Object> variables = var.getVariableMap();
    			leaveService.updateRejectReason(pId,rejectReason);
				taskService.complete(taskId,variables);
				ar.setSucceedMsg("驳回成功");
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg("驳回失败");
			}
    	}
    	return ar;
    }
    
    /**
     * 调整任务
     */
    @RequestMapping(value = "adjust/{taskId}", method = RequestMethod.POST)
   	@ResponseBody
   	public AjaxRes adjust(@PathVariable("taskId")String taskId,String pId,String description,Variable var) {
    	AjaxRes ar=getAjaxRes();
    	if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, TODO_SECURITY_URL))) {
    		try {
    			Map<String, Object> variables = var.getVariableMap();
    			leaveService.updateDescription(pId,description);
				taskService.complete(taskId,variables);
				ar.setSucceedMsg("调整成功");
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg("调整失败");
			}
    	}
    	return ar;
    }
}
