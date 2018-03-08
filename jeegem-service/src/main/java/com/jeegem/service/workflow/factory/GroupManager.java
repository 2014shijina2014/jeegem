package com.jeegem.service.workflow.factory;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;

import com.jeegem.common.utils.ActivitiUtils;
import com.jeegem.entity.system.org.Position;
import com.jeegem.repository.system.account.AccountDao;

public class GroupManager extends GroupEntityManager{

	private AccountDao accountDao;
	
	@Override
	public List<Group> findGroupsByUser(final String userId) {
		List<Position> poss=accountDao.getPoss(userId);
		List<Group> gs= ActivitiUtils.toActivitiGroups(poss);
		return gs;
	}
	
	@Resource
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
		 throw new RuntimeException("not implement method.");  
	}

	@Override
	public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
		 throw new RuntimeException("not implement method.");  
	}

	@Override
	public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
		 throw new RuntimeException("not implement method.");  
	}

	@Override
	public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
		 throw new RuntimeException("not implement method.");  
	}
	
	
}
