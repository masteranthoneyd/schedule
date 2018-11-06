package com.yangbingdong.schedule.fms;

import com.yangbingdong.schedule.domain.root.activity.Activity;
import com.yangbingdong.schedule.domain.root.activity.ActivityEvent;
import com.yangbingdong.schedule.domain.root.activity.ActivityStatus;
import com.yangbingdong.schedule.domain.root.activity.ActivityType;
import com.yangbingdong.schedule.fms.action.CouponActivityEndAction;
import com.yangbingdong.schedule.fms.action.CouponActivityStartAction;
import com.yangbingdong.schedule.fms.core.FakeStateMachineBuilder;
import org.springframework.stereotype.Component;

import static com.yangbingdong.schedule.domain.root.activity.ActivityEvent.END_EVENT;
import static com.yangbingdong.schedule.domain.root.activity.ActivityEvent.START_EVENT;
import static com.yangbingdong.schedule.domain.root.activity.ActivityStatus.ENDED;
import static com.yangbingdong.schedule.domain.root.activity.ActivityStatus.PENDING;
import static com.yangbingdong.schedule.domain.root.activity.ActivityStatus.STARTED;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
@Component
public class CouponActivityStateMachine extends AbstractActivityStateMachine {
	@Override
	public Byte getActType() {
		return ActivityType.COUPON_ACTIVITY.getCode();
	}

	@Override
	public void transitionConfig(FakeStateMachineBuilder<ActivityStatus, ActivityEvent, Activity> machineBuilder) {
		machineBuilder.withTransition().sourceState(PENDING).event(START_EVENT).targetState(STARTED).action(getBeanByClass(CouponActivityStartAction.class))
					  .and()
					  .withTransition().sourceState(STARTED).event(END_EVENT).targetState(ENDED).action(getBeanByClass(CouponActivityEndAction.class))
					  .and()
					  .build();
	}
}
