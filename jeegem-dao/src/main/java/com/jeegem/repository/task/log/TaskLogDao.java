package com.jeegem.repository.task.log;

import com.jeegem.entity.task.log.TaskLog;
import com.jeegem.repository.base.BaseDao;
import com.jeegem.repository.base.JeeGemBatis;
/**
 * 动态任务数据层
 */
@JeeGemBatis
public interface TaskLogDao extends BaseDao<TaskLog>{
	
}
