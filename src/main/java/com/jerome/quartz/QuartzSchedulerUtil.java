package com.jerome.quartz;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Quartz定时调度类
 * 配置文件：quartz.properties
 *
 * @author JeromeThinkPad
 *
<dependency>
	<groupId>org.quartz-scheduler</groupId>
	<artifactId>quartz</artifactId>
	<version>2.3.0</version>
</dependency>
 */
public class QuartzSchedulerUtil {
	private static final Logger LOG = LoggerFactory.getLogger(QuartzSchedulerUtil.class);

	private static Scheduler scheduler = null;

	public static void startQuartz() {
		try {
			// 通过schedulerFactory获取一个调度器
			scheduler = new StdSchedulerFactory().getScheduler();

			// 创建jobDetail实例，绑定Job实现类
			// 指明job的名称，所在组的名称，以及绑定job类
			JobDetail testJob = JobBuilder.newJob(TestJobService.class)
					.withIdentity("testJob", "testJobGroup").build();

			// 定义调度触发规则
			Trigger testTrigger = TriggerBuilder.newTrigger()
					.withIdentity("testTrigger", "testTriggerGroup")
					.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).startNow().build();

			// 把作业和触发器注册到任务调度中
			scheduler.scheduleJob(testJob, testTrigger);

			// 启动调度
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(ExceptionUtils.getFullStackTrace(e));
		}
	}

	public static void closeQuartz() {
		try {
			scheduler.shutdown();
			// scheduler.shutdown(true);
		} catch (SchedulerException e) {
			e.printStackTrace();
			LOG.error(ExceptionUtils.getFullStackTrace(e));
		}
	}

}
