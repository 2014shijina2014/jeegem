package com.jeegem.entity.workflow.online;

import java.util.Date;

import com.jeegem.entity.base.BaseEntity;

public class TaskVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String taskDefinitionKey;
	
	private String name;
	
	private String processDefinitionId;
	
	private String processInstanceId;
	
	private Integer priority;
	
	private Date createTime;
	
	private Date dueDate;
	
	private String description;
	
	private String owner;
	
	private String assignee;
	
	public TaskVo() {
		super();
	}
	
	public TaskVo(String id, String taskDefinitionKey, String name, String processDefinitionId
			, String processInstanceId,Integer priority, Date createTime, Date dueDate
			, String description, String owner, String assignee) {
		super();
		this.id = id;
		this.taskDefinitionKey = taskDefinitionKey;
		this.name = name;
		this.processDefinitionId = processDefinitionId;
		this.processInstanceId = processInstanceId;
		this.priority = priority;
		this.createTime = createTime;
		this.dueDate = dueDate;
		this.description = description;
		this.owner = owner;
		this.assignee = assignee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	
	
}
