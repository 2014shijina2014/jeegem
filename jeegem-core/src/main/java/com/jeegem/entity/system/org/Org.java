package com.jeegem.entity.system.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;
/**
 * 组织机构表
 */
@Alias("BaseOrg")
public class Org extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	/**ID*/
	private String id;
	/**上级ID*/
	private String pId;
	/**上级名*/
	private String pName;
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
	/**子机构*/
	private List<Org> orgs=new ArrayList<Org>();
	/**子角色*/
	private List<Role> roles=new ArrayList<Role>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
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
	public List<Org> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<Org> orgs) {
		this.orgs = orgs;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "Org [id=" + id + ", pId=" + pId + ", name=" + name + "]";
	}
		
}
