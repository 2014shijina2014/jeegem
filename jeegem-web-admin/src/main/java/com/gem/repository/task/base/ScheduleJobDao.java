package com.gem.repository.task.base;

import org.apache.ibatis.annotations.Param;

import com.gem.entity.task.base.ScheduleJob;
import com.gem.repository.base.BaseDao;
import com.gem.repository.base.GemBatis;
/**
 * 动态任务数据层
 */
@GemBatis
public interface ScheduleJobDao extends BaseDao<ScheduleJob>{
	/**
	 * 根据Id获取任务
	 */
	public ScheduleJob getScheduleJobById(@Param("scheduleJobId")String scheduleJobId);
	
}
