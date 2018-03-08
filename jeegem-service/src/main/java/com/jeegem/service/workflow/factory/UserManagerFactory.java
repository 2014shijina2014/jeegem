package com.jeegem.service.workflow.factory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;

public class UserManagerFactory implements SessionFactory{

	private UserEntityManager userEntityManager;
	
	public void setUserEntityManager(UserEntityManager userEntityManager) {
		this.userEntityManager = userEntityManager;
	}

	@Override
	public Class<?> getSessionType() {
		return UserIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return userEntityManager;
	}

}
