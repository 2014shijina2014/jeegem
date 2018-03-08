package com.jeegem.task.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeegem.entity.task.base.ScheduleJob;
import com.jeegem.task.utils.ScheduleUtils;
/* 案例 同步和不同步的区别  非同步需要加@DisallowConcurrentExecution */
public class JobFactory implements Job{
	
	   /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobFactory.class);
    
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
			System.out.println("任务成功运行");
			LOG.info("任务成功运行");
	        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);  		   	
	    	LOG.info("任务名称 = [" + scheduleJob.getJobName() + "]");
	    	System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");
	}

	
}
