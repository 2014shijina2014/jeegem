package com.jeegem.task.job.weixn;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.jeegem.common.utils.SpringWebContextUtil;
import com.jeegem.entity.task.base.ScheduleJob;
import com.jeegem.service.weixin.user.WxFollowerService;
import com.jeegem.task.utils.ScheduleUtils;
import com.jeegem.task.utils.TaskLogUtil;
/**
 * 微信同步关注者任务
 */
public class SyncFollowerJob implements Job{

	   /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(SyncFollowerJob.class);

	@Override
	public void execute(JobExecutionContext context){
        ScheduleJob scheduleJob= (ScheduleJob)context.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY); 
		String jobName=scheduleJob.getJobName();
		String jobGroup=scheduleJob.getJobGroup();
		String jobClass=scheduleJob.getJobClass();
		LOG.info("任务[" + jobName + "]成功运行");
		try {
			ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
			WxFollowerService service = (WxFollowerService) ac.getBean("WxFollowerService");
			service.syncFollower();
			// 保存日志
			TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass,TaskLogUtil.NORMAL, "微信同步关注者任务正常运行");
		} catch (Exception e) {
			LOG.error("任务[" + jobName + "]异常",e);
			// 保存异常日志
			TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass,TaskLogUtil.EXCEPTION,e.toString());
		}
	}
	
}
