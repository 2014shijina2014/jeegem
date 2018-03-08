package com.jeegem.service.workflow.factory;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;

import com.jeegem.common.utils.ActivitiUtils;
import com.jeegem.entity.system.account.Account;
import com.jeegem.entity.system.org.Position;
import com.jeegem.repository.system.account.AccountDao;

public class UserManager extends UserEntityManager{
	
	private AccountDao accountDao;

	@Override
	public User findUserById(String userId) {
		Account account=accountDao.findAccountById(userId);
		UserEntity userEntity=ActivitiUtils.toActivitiUser(account);
		return userEntity;
	}

	@Override
	public List<Group> findGroupsByUser(String userId) {
		List<Position> poss=accountDao.getPoss(userId);
		List<Group> gs= ActivitiUtils.toActivitiGroups(poss);
		return gs;
	}
	
	@Resource
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public List<User> findPotentialStarterUsers(String proceDefId) {
		 throw new RuntimeException("not implement method.");  
	}

	@Override
	public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
		 throw new RuntimeException("not implement method.");  
	}

	@Override
	public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
		 throw new RuntimeException("not implement method.");  
	}

	@Override
	public long findUserCountByQueryCriteria(UserQueryImpl query) {
		 throw new RuntimeException("not implement method.");  
	}

	@Override
	public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
		 throw new RuntimeException("not implement method.");  
	}

	@Override
	public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
		 throw new RuntimeException("not implement method.");  
	}

	@Override
	public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
		 throw new RuntimeException("not implement method.");  
	}

		
}
