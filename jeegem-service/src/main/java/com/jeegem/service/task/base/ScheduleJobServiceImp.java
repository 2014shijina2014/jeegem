package com.jeegem.service.task.base;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeegem.entity.task.base.ScheduleJob;
import com.jeegem.repository.task.base.ScheduleJobDao;
import com.jeegem.service.base.BaseServiceImp;
import com.jeegem.task.utils.ScheduleUtils;

@Service("scheduleJobService")
public class ScheduleJobServiceImp extends BaseServiceImp<ScheduleJob> implements ScheduleJobService {

	/** 调度工厂Bean */
	@Autowired
	private Scheduler scheduler;

	@Override
	public void initScheduleJob() {
		//查找启用的任务
		ScheduleJob aj=new ScheduleJob();
		aj.setStatus(1);	
		List<ScheduleJob> scheduleJobList = baseDao.find(aj);	
		if (CollectionUtils.isNotEmpty(scheduleJobList)) {
			for (ScheduleJob scheduleJob : scheduleJobList) {
				CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobName(),scheduleJob.getJobGroup());
				try {
					if (cronTrigger == null) {
						// 不存在，创建一个
						ScheduleUtils.createScheduleJob(scheduler, scheduleJob);	
					} else {
						// 已存在，那么更新相应的定时设置
						ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
					}
				} catch (Exception e) {
					logger.error("创建定时任务失败",e);
				}
			}
		}
	}

	@Override
	@Transactional
	public int creatScheduleJob(ScheduleJob o) {
		int res=0;
		try {
			o.setCreateTime(new Date());	
			//当状态为启用时
			if(o.getStatus()!=null && o.getStatus()==1){
				ScheduleUtils.createScheduleJob(scheduler,o);		
			}
			//更新数据库
			super.insert(o);
			res=1;
		} catch (Exception e) {
			logger.error("创建任务失败",e);
		}	
		return res;
	}
	@Override
	@Transactional
	public int updateScheduleJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			//先删除
			ScheduleUtils.deleteScheduleJob(scheduler,scheduleJob.getJobName(),scheduleJob.getJobGroup());
			//当状态为启用时
			if(o.getStatus()!=null && o.getStatus()==1){
				ScheduleUtils.createScheduleJob(scheduler, o);		
			}
			//更新数据库
			o.setUpdateTime(new Date());
			dao.update(o);
			res=1;
		} catch (Exception e) {
			logger.error("创建任务失败",e);
		}	
		return res;
	}

	@Override
	@Transactional
	public int deleteScheduleJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			//先删除
			ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(),scheduleJob.getJobGroup());
			//更新数据库
			dao.delete(o);
			res=1;
		}catch (Exception e) {
			logger.error("删除任务失败", e);
		}
		return res;
	}

	@Override
	@Transactional
	public int runOnce(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			if(scheduleJob.getStatus()!=null && scheduleJob.getStatus()==1){
				//运行一次任务
				res=2;
			}else{
				//当任务没启动，必须先创建
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
				//时间短可能促发多次
				//ScheduleUtils.pauseJob(scheduler,scheduleJob.getJobName(), scheduleJob.getJobGroup());
				//然后立刻运行一次任务
				ScheduleUtils.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
				try {
					//休眠3秒，等任务完成，完成不了就加长休眠时间吧...
			        Thread.sleep(3000);
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
				//再删除任务
				ScheduleUtils.deleteScheduleJob(scheduler,scheduleJob.getJobName(), scheduleJob.getJobGroup());
				res=1;
			}			
		} catch (Exception e) {
			logger.error("运行一次定时任务失败", e);
		}
		return res;
	}

	@Override
	@Transactional
	public int pauseJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			if(scheduleJob.getStatus()!=null && scheduleJob.getStatus()==1){
				//判断jobKey为不为空，如为空，任务已停止
				//先暂停任务
				//ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());		
				ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
				//更新数据库
				scheduleJob.setStatus(0);
				scheduleJob.setUpdateTime(new Date());
				dao.update(scheduleJob);
				res=1;
			}else{	
				//任务没启动，谈何暂停...
				res=2;			
			}
		} catch (Exception e) {
			logger.error("暂停定时任务失败", e);
		}
		return res;
	}

	@Override
	@Transactional
	public int resumeJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			if(scheduleJob.getStatus()!=null && scheduleJob.getStatus()==0){
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
				//更新数据库
				scheduleJob.setStatus(1);
				scheduleJob.setUpdateTime(new Date());
				dao.update(scheduleJob);
				res=1;
			}else{
				res=2;
			}
		} catch (Exception e) {
			logger.error("恢复定时任务失败", e);
		}
		return res;
	}
	

}
