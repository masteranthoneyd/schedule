package com.yangbingdong.schedule.fms.action;

import com.yangbingdong.schedule.domain.repository.ActivityRepository;
import com.yangbingdong.schedule.domain.root.activity.Activity;
import com.yangbingdong.schedule.domain.root.activity.ActivityEvent;
import com.yangbingdong.schedule.domain.root.activity.ActivityStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
@Component
@Slf4j
public class CouponActivityEndAction implements ActivityStateChangeAction {

	@Resource
	private ActivityRepository activityRepository;

	@Override
	public void doStateChangeAction(ActivityStatus activityStatus, ActivityEvent event, Activity activity) {
		log.info("优惠券活动结束了...");
		activity.setStatus(activityStatus.getCode());
		activityRepository.updateActivity(activity);
	}
}
