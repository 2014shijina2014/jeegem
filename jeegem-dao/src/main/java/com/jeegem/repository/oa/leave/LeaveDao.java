package com.jeegem.repository.oa.leave;

import org.apache.ibatis.annotations.Param;

import com.jeegem.entity.oa.leave.Leave;
import com.jeegem.repository.base.BaseDao;
import com.jeegem.repository.base.JeeGemBatis;
/**
 * 请假数据层
 */
@JeeGemBatis
public interface LeaveDao extends BaseDao<Leave>{
	
	public Leave findLeaveByPId(@Param("pId")String pId);
	
	public void updateRejectReason(Leave leave);
	
	public void updateDescription(Leave leave);
}
