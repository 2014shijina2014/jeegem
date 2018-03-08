package com.jeegem.entity.system.org;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;
/**
 * 职位管理
 */
@Alias("BasePos")
public class Position extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private String id;
	/**机构Id*/
	private String orgId;	
	/**职位名称*/
	private String name;	
	/**职位类型*/
	private String type;	
	/**描述*/
	private String description;	
	/**创建时间*/
	private Date createTime;	
	/**修改时间*/
	private Date updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
	private String keyWord;

	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	private String allOrgIds;

	public String getAllOrgIds() {
		return allOrgIds;
	}
	public void setAllOrgIds(String allOrgIds) {
		this.allOrgIds = allOrgIds;
	}
}
