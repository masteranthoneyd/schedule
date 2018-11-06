package com.yangbingdong.schedule.fms.action;

import com.yangbingdong.schedule.domain.root.activity.Activity;
import com.yangbingdong.schedule.domain.root.activity.ActivityEvent;
import com.yangbingdong.schedule.domain.root.activity.ActivityStatus;
import com.yangbingdong.schedule.fms.core.Action;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
public interface ActivityStateChangeAction extends Action<ActivityStatus, ActivityEvent, Activity> {
}
