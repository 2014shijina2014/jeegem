package com.jeegem.entity.system.resources;

import com.jeegem.entity.base.BaseEntity;

public class RoleResources extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String roleId;
	
	private String resourcesId;

	public RoleResources() {}

	public RoleResources(String roleId, String resourcesId) {
		this.roleId = roleId;
		this.resourcesId = resourcesId;
	}



	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
	}
	
	
}
