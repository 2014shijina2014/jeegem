package com.jeegem.service.system.org;

import java.util.List;

import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.tree.entity.ZNodes;
import com.jeegem.entity.system.account.Account;
import com.jeegem.entity.system.org.Position;
import com.jeegem.service.base.BaseService;

public interface PositionService extends BaseService<Position>{
	/**
	 * 获得岗位书树
	 * @return
	 */
	 public List<ZNodes> getOrgAndPositionTree();	 
	 /**
	  * 获得上级组织树
	  * @return
	  */
	 public List<ZNodes> getPreOrgTree();
	 /**
	  * 保存用户职务表 
	  * @param list
	 */
	 public void saveAccountPosition(String posId,String ids);  
	 /**
	 * 通过职位Id寻找可安排用户列表
	 * @param Position 
	 */
	 public Page<Account> findArrangeAccByPage(Position o,Page<Account> page);
	 /**
	 * 通过职位Id寻找已安排用户列表
	 * @param Position 
	 */
	 public Page<Account> findPosByPage(Position o,Page<Account> page);
	 /**
	 * 删除用户职务表 
	 * @param accountId 用户Id
	 */
	 public void deleteAccPosByAccId(String accountId);
	 /**
	  * 删除职务
	  */
	 public void deletePos(Position o);
	 
	/**
	 * 获取所有职务
	 * @return
	 */
	public Page<Position> findAllPosByPage(Position o, Page<Position> page);
}
