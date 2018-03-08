package com.jeegem.controller.workflow.process;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.base.Const;
import com.jeegem.controller.base.BaseController;
import com.jeegem.entity.workflow.process.ProcessDefinitionVo;
/**
 * 流程定义管理
 */
@Controller
@RequestMapping(value = "/backstage/workflow/process/")
public class ProcessController extends BaseController<Object>{

	@Autowired
	private RepositoryService repositoryService;
	/**
	 * 流程定义列表
	 */
	@RequestMapping(value = "index")
	public String index(org.springframework.ui.Model model) {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/workflow/process/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	/**
	 * 查找流程定义列表
	 * @param page    分页类
	 * @param keyWord 关键字
	 * @return
	 */
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ProcessDefinitionVo> page,String keyWord) {
		AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/workflow/process/index"))) {
			try {
				int pageNum=page.getPageNum()-1;
				int pageSize=page.getPageSize();
				ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery();
				List<ProcessDefinition> processDefinitionList =new ArrayList<ProcessDefinition>();
				List<ProcessDefinitionVo> pdvoList = new ArrayList<ProcessDefinitionVo>();
				if(StringUtils.isNotBlank(keyWord)){
					processDefinitionList = query.processDefinitionNameLike("%"+keyWord+"%")
							.orderByDeploymentId().desc()
							.listPage(pageNum, pageSize);
				}else{
					processDefinitionList = query.orderByDeploymentId().desc()
							.listPage(pageNum, pageSize);
				}
				for (ProcessDefinition pd : processDefinitionList) {
					String deploymentId = pd.getDeploymentId();
					Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
					ProcessDefinitionVo pdvo=new ProcessDefinitionVo(pd.getId(),deploymentId
							,pd.getName(),pd.getKey(),pd.getVersion(),deployment.getDeploymentTime()
							,pd.getResourceName(),pd.getDiagramResourceName());		
					pdvoList.add(pdvo);
				}
				long count=query.count();
				page.setTotalRecord((int) count);
				page.setResults(pdvoList);
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
     * 删除部署的流程，级联删除流程实例
     *
     * @param deploymentId 流程部署ID
     */
    @RequestMapping(value = "del",method=RequestMethod.POST)
    @ResponseBody
	public AjaxRes del(String processDefinitionId){
    	AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
			try {
				ProcessDefinition pd =repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
				repositoryService.deleteDeployment(pd.getDeploymentId(),true);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
    
    @RequestMapping(value = "uploadModel")
    @ResponseBody
    public AjaxRes uploadModel(@RequestParam(value = "modelFile", required = false) MultipartFile file){
    	AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
			try {	
				String fileName = file.getOriginalFilename();
				InputStream fileInputStream = file.getInputStream();
				Deployment deployment = null;
				String extension = FilenameUtils.getExtension(fileName);
				if (extension.equals("zip") || extension.equals("bar")) {
					ZipInputStream zip = new ZipInputStream(fileInputStream);
					deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
				} else {
					deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
				}
				if(deployment!=null){
					ar.setSucceedMsg("上传成功");
				}
			} catch (Exception e) {
				logger.error("error on deploy process, because of file input stream", e);
				ar.setFailMsg("上传失败");
			}
		}
        return ar;
    }
    
    @RequestMapping(value = "convertToModel")
    @ResponseBody
    public AjaxRes convertToModel(String processDefinitionId){
    	AjaxRes ar=getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {	
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
						.processDefinitionId(processDefinitionId).singleResult();
				InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
						processDefinition.getResourceName());
				XMLInputFactory xif = XMLInputFactory.newInstance();
				InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
				XMLStreamReader xtr = xif.createXMLStreamReader(in);
				BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

				BpmnJsonConverter converter = new BpmnJsonConverter();
				com.fasterxml.jackson.databind.node.ObjectNode modelNode = converter.convertToJson(bpmnModel);
				Model modelData = repositoryService.newModel();
				modelData.setKey(processDefinition.getKey());
				modelData.setName(processDefinition.getName());
				modelData.setCategory(processDefinition.getDeploymentId());

				ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
				modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
				modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
				modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
				modelData.setMetaInfo(modelObjectNode.toString());

				repositoryService.saveModel(modelData);
				repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
				ar.setSucceedMsg("转换成功");
			} catch (Exception e) {
				logger.error("转换模型失败", e);
				ar.setFailMsg("转换失败");
			}
		}
        return ar;
    }
    
    
    /**
     * 读取资源，通过部署ID
     *
     * @param processDefinitionId 流程定义
     * @param resourceType        资源类型(xml|image)
     * @throws Exception
     */
    @RequestMapping(value = "resource/read")
    public void loadByDeployment(@RequestParam("processDefinitionId") String processDefinitionId, @RequestParam("resourceType") String resourceType,
                                 HttpServletResponse response) throws Exception {
    	response.setCharacterEncoding("UTF-8");
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        String resourceName = "";
        if (resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
}
