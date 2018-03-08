package com.jeegem.controller.workflow.model;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.base.Const;
import com.jeegem.controller.base.BaseController;

/**
 * 流程模型控制器
 */
@Controller
@RequestMapping(value = "/backstage/workflow/model/")
public class ModelController extends BaseController<Object>{

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 模型列表
     */
    @RequestMapping(value = "index")
    public String index(org.springframework.ui.Model model) {
    	if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){	
    		model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/workflow/model/list";
		}
		return Const.NO_AUTHORIZED_URL;
    }
	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<Model> page,String keyWord) {
		AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/workflow/model/index"))) {
			try {
				int pageNum=page.getPageNum()-1;
				int pageSize=page.getPageSize();
				ModelQuery modelQuery=repositoryService.createModelQuery();
				List<Model> list =new ArrayList<Model>();
				if(StringUtils.isNotBlank(keyWord)){
					list = modelQuery.modelNameLike("%"+keyWord+"%")
							.orderByCreateTime().desc()
							.listPage(pageNum, pageSize);
				}else{
					list = modelQuery.orderByCreateTime().desc()
							.listPage(pageNum, pageSize);
				}
				long count=modelQuery.count();
				page.setTotalRecord((int) count);
				page.setResults(list);
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

	
    /**
     * 创建模型
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public void create(String name,String key,String description,HttpServletRequest request, HttpServletResponse response) {
    	if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){			
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            ObjectNode editorNode = objectMapper.createObjectNode();
	            editorNode.put("id", "canvas");
	            editorNode.put("resourceId", "canvas");
	            ObjectNode stencilSetNode = objectMapper.createObjectNode();
	            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
	            editorNode.put("stencilset", stencilSetNode);
	            Model modelData = repositoryService.newModel();
	
	            ObjectNode modelObjectNode = objectMapper.createObjectNode();
	            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
	            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
	            description = StringUtils.defaultString(description);
	            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
	            modelData.setMetaInfo(modelObjectNode.toString());
	            modelData.setName(name);
	            modelData.setKey(StringUtils.defaultString(key));
	
	            repositoryService.saveModel(modelData);
	            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
	
	            response.sendRedirect(request.getContextPath() + "/act-process-editor/modeler.html?modelId=" + modelData.getId());
	        } catch (Exception e) {
	            logger.error("创建模型失败", e);
	        }
    	}else{
    		 try {
				response.sendRedirect(request.getContextPath() +"/backstage/noAuthorized");
			} catch (Exception e) {
				logger.error(e.toString(),e);
			}
    	}
    }

	 /**
     * 编辑模型
     */
	@RequestMapping(value = "edit")
    public void edit(String modelId,HttpServletRequest request, HttpServletResponse response) {
		 try {
			if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){			
				response.sendRedirect(request.getContextPath() + "/act-process-editor/modeler.html?modelId=" + modelId);
			}else{
				response.sendRedirect(request.getContextPath() +"/backstage/noAuthorized");
			}		
		 }catch (Exception e) {
				logger.error(e.toString(),e);
		}			
	}
    
    /**
     * 根据Model部署流程
     */
    @RequestMapping(value = "deploy",method=RequestMethod.POST)
    @ResponseBody
    public AjaxRes deploy(String modelId) {
    	AjaxRes ar=getAjaxRes();
    	if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
    		try {        	
				Model modelData = repositoryService.getModel(modelId);
				ObjectNode modelNode = (ObjectNode) new ObjectMapper()
				.readTree(repositoryService.getModelEditorSource(modelData.getId()));
				byte[] bpmnBytes = null;
				BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
				bpmnBytes = new BpmnXMLConverter().convertToXML(model);
				String processName = modelData.getName() + ".bpmn20.xml";
				Deployment deployment = repositoryService.createDeployment()
						.name(modelData.getName()).addString(processName, new String(bpmnBytes,"UTF-8")).deploy();
				ar.setSucceedMsg("模型部署流程成功");
				logger.info("模型部署流程成功，部署ID=" + deployment.getId());
    	    } catch (Exception e) {
				logger.error("根据模型部署流程失败：modelId={}", modelId, e);
				ar.setFailMsg("模型部署流程失败");
    	    }
    	}   
        return ar;
    }

    /**
     * 导出model对象为指定类型
     * @param modelId 模型ID
     * @param type 导出文件类型(bpmn\json)
     */
    @RequestMapping(value = "export/{modelId}/{type}")
    public void export(@PathVariable("modelId") String modelId,@PathVariable("type") String type,
                       HttpServletResponse response) {
	    if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/backstage/workflow/model/export")){
	    	try {
	            Model modelData = repositoryService.getModel(modelId);
	            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
	            byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());
	
	            JsonNode editorNode = new ObjectMapper().readTree(modelEditorSource);
	            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
	
	            // 处理异常
	            if (bpmnModel.getMainProcess() == null) {
	                response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
	                response.getOutputStream().println("no main process, can't export for type: " + type);
	                response.flushBuffer();
	                return;
	            }
	            String filename = "";
	            byte[] exportBytes = null;	
	            String mainProcessId = bpmnModel.getMainProcess().getId();
	            switch (type) {
	                case "bpmn": {
	                    BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
	                    exportBytes = xmlConverter.convertToXML(bpmnModel);
	                    filename = mainProcessId + ".bpmn20.xml";
	                    break;
	                }	
	                case "json": {
	                    exportBytes = modelEditorSource;
	                    filename = mainProcessId + ".json";
	                    break;
	                }
	            }	
	            ByteArrayInputStream in = new ByteArrayInputStream(exportBytes);
	            IOUtils.copy(in, response.getOutputStream());
	
	            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
	            response.flushBuffer();
	        } catch (Exception e) {
	            logger.error("导出model的xml文件失败：modelId={}, type={}", modelId, type, e);
	        }
	    }        
    }

    @RequestMapping(value = "del",method=RequestMethod.POST)
    @ResponseBody
	public AjaxRes del(String modelId){
    	AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
			try {
				repositoryService.deleteModel(modelId);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}		
		return ar;
	}
}
