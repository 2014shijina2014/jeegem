package com.gem.repository.oa.leave;

import org.apache.ibatis.annotations.Param;

import com.gem.entity.oa.leave.Leave;
import com.gem.repository.base.BaseDao;
import com.gem.repository.base.GemBatis;
/**
 * 请假数据层
 */
@GemBatis
public interface LeaveDao extends BaseDao<Leave>{
	
	public Leave findLeaveByPId(@Param("pId")String pId);
	
	public void updateRejectReason(Leave leave);
	
	public void updateDescription(Leave leave);
}
