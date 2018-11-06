package com.yangbingdong.schedule.config.quartz;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ybd
 * @date 18-11-2
 * @contact yangbingdong1994@gmail.com
 */
@Component
public class CustomizedActivitySchedulerFactory implements JobFactory, ApplicationContextAware {

	private ApplicationContext applicationContext;


	@Override
	public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) {
		return applicationContext.getBean(bundle.getJobDetail().getJobClass());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
