package com.jeegem.entity.system.org;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;
/**
 * 用户角色表
 */
@Alias("BaseRole")
public class Role extends BaseEntity{
	private static final long serialVersionUID = 1L;
	/**ID*/
	private String id;
	/**机构Id*/
	private String orgId;	
	/**名称*/
	private String name;	
	/**状态*/
	private Integer isValid;
	/**描述*/
	private String description;	
	/**创建时间*/
	private Date createTime;	
	/**修改时间*/
	private Date updateTime;	
	private String keyWord;
	
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

	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", orgId=" + orgId + ", name=" + name + "]";
	}
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
