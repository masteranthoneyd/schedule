package com.yangbingdong.schedule.domain.repository;

import com.yangbingdong.schedule.domain.root.activity.Activity;
import com.yangbingdong.schedule.domain.root.activity.ActivityStatus;
import com.yangbingdong.schedule.domain.root.activity.ActivityType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
@Component
public class ActivityRepository {
	private static final Map<Long, Activity> MAP = new HashMap<>(1 << 4);

	static {
		Activity activity = new Activity();
		activity.setActId(1L)
				.setActType(ActivityType.COUPON_ACTIVITY.getCode())
				.setStatus(ActivityStatus.PENDING.getCode());
		MAP.put(activity.getActId(), activity);
	}

	public Activity getActivityById(Long id) {
		return MAP.get(id);
	}

	public void updateActivity(Activity activity) {
		MAP.put(activity.getActId(), activity);
	}
}
