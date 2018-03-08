package com.jeegem.entity.workflow.process;

import java.util.Date;

import com.jeegem.entity.base.BaseEntity;

public class ProcessDefinitionVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String deploymentId;
	
	private String name;
	
	private String key;
	
	private int version;
	
	private Date deploymentTime;
	
	private String resourceName;
	
	private String diagramResourceName;
	
	public ProcessDefinitionVo(){
		
	}
	/**
	 * 
	 * @param id
	 * @param deploymentId
	 * @param name
	 * @param key
	 * @param version
	 * @param deploymentTime
	 */
	public ProcessDefinitionVo(String id, String deploymentId, String name, String key
			,int version,Date deploymentTime
			,String resourceName,String diagramResourceName) {
		super();
		this.id = id;
		this.deploymentId = deploymentId;
		this.name = name;
		this.key = key;
		this.version = version;
		this.deploymentTime=deploymentTime;
		this.resourceName=resourceName;
		this.diagramResourceName=diagramResourceName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Date getDeploymentTime() {
		return deploymentTime;
	}
	public void setDeploymentTime(Date deploymentTime) {
		this.deploymentTime = deploymentTime;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getDiagramResourceName() {
		return diagramResourceName;
	}
	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}
	
}
