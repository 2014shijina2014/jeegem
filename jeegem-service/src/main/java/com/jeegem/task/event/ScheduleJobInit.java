package com.jeegem.task.event;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeegem.service.task.base.ScheduleJobService;


/**
 * 定时任务初始化
 */
@Component
public class ScheduleJobInit {

    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleJobInit.class);

    /** 定时任务service */
    @Autowired
    private ScheduleJobService  scheduleJobService;

    /**
     * 项目启动时初始化
     */
    @PostConstruct
    public void init() {
        LOG.info("定时任务初始化init");
        scheduleJobService.initScheduleJob();
        LOG.info("定时任务初始化end");
    }

}
