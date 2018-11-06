package com.yangbingdong.schedule.config.quartz;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ybd
 * @date 18-11-2
 * @contact yangbingdong1994@gmail.com
 */
@Component
public class QuartzScheduleFactoryBeanCustomizer implements SchedulerFactoryBeanCustomizer {

	@Resource
	private CustomizedActivitySchedulerFactory customizedSchedulerFactory;

	@Override
	public void customize(SchedulerFactoryBean schedulerFactoryBean) {
		schedulerFactoryBean.setJobFactory(customizedSchedulerFactory);
	}
}
