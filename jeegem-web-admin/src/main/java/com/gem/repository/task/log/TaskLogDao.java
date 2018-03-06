package com.gem.repository.task.log;

import com.gem.entity.task.log.TaskLog;
import com.gem.repository.base.BaseDao;
import com.gem.repository.base.GemBatis;
/**
 * 动态任务数据层
 */
@GemBatis
public interface TaskLogDao extends BaseDao<TaskLog>{
	
}
