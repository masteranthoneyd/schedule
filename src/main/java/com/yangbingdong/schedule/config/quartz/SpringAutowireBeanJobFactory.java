package com.yangbingdong.schedule.config.quartz;

/**
 * @author ybd
 * @date 18-10-31
 * @contact yangbingdong1994@gmail.com
 *
 * Spring Boot 2.0 已对 Quartz 自动装配 {@link org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration}
 */
/*@Component
public class SpringAutowireBeanJobFactory extends SpringBeanJobFactory {

	@Resource
	private AutowireCapableBeanFactory capableBeanFactory;

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Object job = super.createJobInstance(bundle);
		capableBeanFactory.autowireBean(job);
		capableBeanFactory.initializeBean(job, null);
		return job;
	}
}*/
