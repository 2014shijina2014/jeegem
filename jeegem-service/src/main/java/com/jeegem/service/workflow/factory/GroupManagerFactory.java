package com.jeegem.service.workflow.factory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

public class GroupManagerFactory implements SessionFactory {
	
	private GroupEntityManager groupEntityManager;
	
	public void setGroupEntityManager(GroupEntityManager groupEntityManager) {
	        this.groupEntityManager = groupEntityManager;
	}
	
	@Override
	public Class<?> getSessionType() {
		return GroupIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return groupEntityManager;
	}

}
