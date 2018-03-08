package com.jeegem.controller.workflow.online.his;

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
import com.jeegem.common.utils.security.AccountShiroUtil;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.workflow.process.HistoricProcessInstanceVo;
/**
 * 在线结束任务
 */
@Controller
@RequestMapping(value = "/backstage/workflow/online/his/")
public class HisController extends BaseController<Object>{

	private static final String SECURITY_URL="/backstage/workflow/online/his/index";
	 @Autowired
	 private HistoryService historyService;
    /**
     * 任务列表
     */
    @RequestMapping(value = "index")
    public String index(org.springframework.ui.Model model) {
    	if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){	
    		model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/workflow/online/his/list";
		}
		return Const.NO_AUTHORIZED_URL;
    }
 
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<HistoricProcessInstanceVo> page,String keyWord) {
    	AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				int pageNum=page.getPageNum()-1;
				int pageSize=page.getPageSize();
				List<HistoricProcessInstanceVo> vos=new ArrayList<HistoricProcessInstanceVo>();
				String currentUserId = AccountShiroUtil.getCurrentUser().getAccountId();			
				// 根据当前人的ID查询
				HistoricProcessInstanceQuery query=historyService.createHistoricProcessInstanceQuery()
						.startedBy(currentUserId).finished()
						.orderByProcessInstanceEndTime().desc();
				List<HistoricProcessInstance> list=query.listPage(pageNum, pageSize);
				for(HistoricProcessInstance t:list){	
					HistoricProcessInstanceVo vo=new HistoricProcessInstanceVo(t.getId(),t.getProcessDefinitionId(),
							t.getStartTime(),t.getEndTime(),t.getDeleteReason());					
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
