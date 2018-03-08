package com.jeegem.repository.task.base;

import org.apache.ibatis.annotations.Param;

import com.jeegem.entity.task.base.ScheduleJob;
import com.jeegem.repository.base.BaseDao;
import com.jeegem.repository.base.JeeGemBatis;
/**
 * 动态任务数据层
 */
@JeeGemBatis
public interface ScheduleJobDao extends BaseDao<ScheduleJob>{
	/**
	 * 根据Id获取任务
	 */
	public ScheduleJob getScheduleJobById(@Param("scheduleJobId")String scheduleJobId);
	
}
