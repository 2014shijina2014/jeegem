package com.jeegem.controller.workflow.online.apply;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.security.AccountShiroUtil;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.oa.leave.Leave;
import com.jeegem.service.oa.leave.LeaveService;
/**
 * 申请页面
 *
 */
@Controller
@RequestMapping(value = "/backstage/workflow/online/apply/")
public class ApplyController extends BaseController<Object>{

	private static final String SECURITY_URL="/backstage/workflow/online/apply/index";
	
	 @Autowired
	 private RuntimeService runtimeService;
	 @Autowired
	 private IdentityService identityService; 
	 @Autowired
	 private LeaveService leaveService;
    /**
     * 申请列表
     */
    @RequestMapping(value = "index")
    public String index(org.springframework.ui.Model model) {
    	if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){	
    		model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/workflow/online/apply/list";
		}
		return Const.NO_AUTHORIZED_URL;
    }

    
    /**
     * 启动流程
     */
    @RequestMapping(value = "start", method = RequestMethod.POST)
	@ResponseBody
    public AjaxRes startWorkflow(Leave leave) {
    	AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				String currentUserId = AccountShiroUtil.getCurrentUser().getAccountId();
				Map<String, Object> variables = new HashMap<String, Object>();
				identityService.setAuthenticatedUserId(currentUserId);
				ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave",variables);
				String pId=processInstance.getId();
				leave.setpId(pId);
				leave.setAccount_id(currentUserId);
				leave.setCreateTime(new Date());
				leave.setId(get32UUID());
				leaveService.insert(leave);
				ar.setSucceedMsg("流程已启动，流程ID：" + processInstance.getId());	
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg("启动流程失败");	
			}finally{
				identityService.setAuthenticatedUserId(null);
			}
		}
		return ar;
    }
    
}
