package com.yangbingdong.schedule.fms;

import com.yangbingdong.schedule.domain.root.activity.Activity;
import com.yangbingdong.schedule.domain.root.activity.ActivityEvent;
import com.yangbingdong.schedule.domain.root.activity.ActivityStatus;
import com.yangbingdong.schedule.fms.core.AbstractFakeStateMachine;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
public abstract class AbstractActivityStateMachine extends AbstractFakeStateMachine<ActivityStatus, ActivityEvent, Activity> implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	public abstract Byte getActType();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	<T> T getBeanByClass(Class<T> beanClass) {
		return applicationContext.getBean(beanClass);
	}
}
