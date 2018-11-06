package com.yangbingdong.schedule.job;

import com.yangbingdong.schedule.domain.repository.ActivityRepository;
import com.yangbingdong.schedule.domain.root.ScheduleJobInfo;
import com.yangbingdong.schedule.domain.root.activity.Activity;
import com.yangbingdong.schedule.domain.root.activity.ActivityEvent;
import com.yangbingdong.schedule.fms.AbstractActivityStateMachine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ybd
 * @date 18-10-31
 * @contact yangbingdong1994@gmail.com
 */
@Slf4j
@Component
public class ActivityScheduleJob extends BaseDynamicScheduleJob {

	@Resource
	private ActivityStateMachineMapHolder stateMachineMapHolder;

	@Resource
	private ActivityRepository activityRepository;


	@Override
	protected void executeInternal(ScheduleJobInfo scheduleJobInfo) throws Exception {
		AbstractActivityStateMachine stateMachine = stateMachineMapHolder.getStateMachineByCode(scheduleJobInfo.getActType());
		Objects.requireNonNull(stateMachine, "状态机为空, actType = " + scheduleJobInfo.getActType());
		Activity activity = activityRepository.getActivityById(scheduleJobInfo.getActId());
		Objects.requireNonNull(activity, "活动为空, actId = " + scheduleJobInfo.getActId());
		stateMachine.fire(activity, ActivityEvent.getByCode(scheduleJobInfo.getEventCode()));
	}
}
