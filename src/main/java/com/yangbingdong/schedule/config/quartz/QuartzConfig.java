package com.yangbingdong.schedule.config.quartz;

import com.yangbingdong.schedule.core.ScheduleJobOperations;
import com.yangbingdong.schedule.core.SpringQuartzJobTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ybd
 * @date 18-10-31
 * @contact yangbingdong1994@gmail.com
 *
 * Spring Boot 2.0 已对 Quartz 自动装配 {@see QuartzAutoConfiguration}
 */
@Configuration
public class QuartzConfig {

	/*@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		bean.setJobFactory(springAutowireBeanJobFactory);
		return bean;
	}*/

	@Bean
	public ScheduleJobOperations scheduleJobOperations() {
		return new SpringQuartzJobTemplate();
	}
}
