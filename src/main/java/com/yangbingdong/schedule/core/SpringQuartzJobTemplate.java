package com.yangbingdong.schedule.core;

import com.yangbingdong.schedule.domain.root.ScheduleJobInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;

import static com.yangbingdong.schedule.job.BaseDynamicScheduleJob.SCHEDULE_JOB_ID_KEY;
import static org.quartz.Scheduler.DEFAULT_GROUP;

/**
 * @author ybd
 * @date 18-10-31
 * @contact yangbingdong1994@gmail.com
 */
@Slf4j
public class SpringQuartzJobTemplate implements ScheduleJobOperations, InitializingBean {

	@Resource
	private SchedulerFactoryBean schedulerFactoryBean;

	private Scheduler scheduler;

	@Override
	public void addScheduleJob(ScheduleJobInfo scheduleJobInfo) {
		try {
			JobKey jobKey = parseJobKey(scheduleJobInfo);
			TriggerKey triggerKey = parseTriggerKey(scheduleJobInfo);
			Class<? extends Job> jobClass = Class.forName(scheduleJobInfo.getJobClass()).asSubclass(Job.class);
			JobDetail jobDetail = JobBuilder.newJob(jobClass)
											.withIdentity(jobKey)
											.build();
			jobDetail.getJobDataMap().put(SCHEDULE_JOB_ID_KEY, scheduleJobInfo.getId());
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobInfo.getCron());
			CronTrigger cronTrigger = TriggerBuilder.newTrigger()
													.withIdentity(triggerKey)
													.forJob(jobKey)
													.withSchedule(cronScheduleBuilder)
													.build();
			boolean jobExists = scheduler.checkExists(jobKey);
			boolean triggerExists = scheduler.checkExists(triggerKey);
			if (!jobExists && !triggerExists) {
				scheduler.scheduleJob(jobDetail, cronTrigger);
			} else if (jobExists && !triggerExists) {
				scheduler.scheduleJob(cronTrigger);
			} else {
				log.info("Already exists trigger {} with existing {}", triggerKey, jobKey);
			}
			log.info("Success [CREATE] quartz job: " + jobKey);
		} catch (Exception e) {
			throw new ScheduleJobException(e);
		}
	}

	@Override
	public void deleteScheduleJob(ScheduleJobInfo scheduleJobInfo) {
		try {
			TriggerKey triggerKey = parseTriggerKey(scheduleJobInfo);
			if (scheduler.checkExists(triggerKey)) {
				scheduler.pauseTrigger(triggerKey);
				scheduler.unscheduleJob(triggerKey);
				JobKey jobKey = parseJobKey(scheduleJobInfo);
				if (scheduler.checkExists(jobKey)) {
					scheduler.deleteJob(parseJobKey(scheduleJobInfo));
				}
				log.info("Success create quartz job: " + triggerKey.toString());
			} else {
				log.info("Fail to [DELETE] schedule job, job not exist: " + triggerKey);
			}
		} catch (SchedulerException e) {
			throw new ScheduleJobException(e);
		}
	}

	@Override
	public void updateScheduleJob(ScheduleJobInfo scheduleJobInfo) {
		try {
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobInfo.getCron());
			TriggerKey oldTriggerKey = parseTriggerKey(scheduleJobInfo);
			CronTrigger newTrigger = TriggerBuilder.newTrigger()
													.withIdentity(oldTriggerKey)
													.withSchedule(cronScheduleBuilder)
													.build();
			scheduler.rescheduleJob(oldTriggerKey, newTrigger);
			log.info("Success [UPDATE] quartz job: " + oldTriggerKey);
		} catch (Exception e) {
			throw new ScheduleJobException(e);
		}
	}

	@Override
	public void addOrUpdateScheduleJob(ScheduleJobInfo scheduleJobInfo) {
		try {
			TriggerKey triggerKey = parseTriggerKey(scheduleJobInfo);
			JobKey jobKey = parseJobKey(scheduleJobInfo);
			if (scheduler.checkExists(triggerKey) && scheduler.checkExists(jobKey)) {
				updateScheduleJob(scheduleJobInfo);
			} else {
				addScheduleJob(scheduleJobInfo);
			}
		} catch (Exception e) {
			throw new ScheduleJobException(e);
		}
	}

	@Override
	public void trigger(ScheduleJobInfo scheduleJobInfo) {
		try {
			scheduler.triggerJob(parseJobKey(scheduleJobInfo));
		} catch (SchedulerException e) {
			throw new ScheduleJobException(e);
		}
	}

	@Override
	public void afterPropertiesSet() {
		this.scheduler = schedulerFactoryBean.getScheduler();
	}

	private TriggerKey parseTriggerKey(ScheduleJobInfo scheduleJobInfo) {
		return TriggerKey.triggerKey(scheduleJobInfo.getJobName(), DEFAULT_GROUP);
	}

	private JobKey parseJobKey(ScheduleJobInfo scheduleJobInfo) {
		return JobKey.jobKey(scheduleJobInfo.getJobName(), DEFAULT_GROUP);
	}
}
