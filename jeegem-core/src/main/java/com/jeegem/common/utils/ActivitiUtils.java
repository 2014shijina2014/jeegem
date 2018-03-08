package com.jeegem.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import com.jeegem.entity.system.account.Account;
import com.jeegem.entity.system.org.Position;

public class ActivitiUtils {
	
	public static UserEntity  toActivitiUser(Account accout){
		UserEntity userEntity = new UserEntity();
		userEntity.setId(accout.getAccountId());
		userEntity.setRevision(1);
		return userEntity;
	}
	
	public static GroupEntity  toActivitiGroup(Position bGroup){
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setRevision(1);
		groupEntity.setType("assignment");
		groupEntity.setId(bGroup.getId());
		groupEntity.setName(bGroup.getName());
		return groupEntity;
	}
	
	public static List<Group> toActivitiGroups(List<Position> bGroups){
		List<Group> groupEntitys = new ArrayList<Group>();
		for (Position bGroup : bGroups) {
			GroupEntity groupEntity = toActivitiGroup(bGroup);
			groupEntitys.add(groupEntity);
		}
		return groupEntitys;
	}
}
