package com.jeegem.entity.task.log;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;
@Alias("TaskLog")
public class TaskLog extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;
	/** 日志名称*/
	private String name;
	/** 类名 */
	private String className;
	/** 日志类型  1：正常，2:异常*/ 
	private Integer type;
	/** 日志描述*/
	private String description;
	/** 创建时间 */
	private Date createTime;
	
	private String keyWord;
	
	private String beginTime;
	
	private String endTime;
	
	public TaskLog() {}
	
	public TaskLog(String id, String name, String className, Integer type, String description, Date createTime) {
		this.id = id;
		this.name = name;
		this.className = className;
		this.type = type;
		this.description = description;
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
		
}
