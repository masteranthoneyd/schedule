package com.yangbingdong.schedule.core;

import com.yangbingdong.schedule.domain.root.ScheduleJobInfo;

/**
 * @author ybd
 * @date 18-10-31
 * @contact yangbingdong1994@gmail.com
 */
public interface ScheduleJobOperations {

	void addScheduleJob(ScheduleJobInfo scheduleJobInfo);

	void deleteScheduleJob(ScheduleJobInfo scheduleJobInfo);

	void updateScheduleJob(ScheduleJobInfo scheduleJobInfo);

	void addOrUpdateScheduleJob(ScheduleJobInfo scheduleJobInfo);

	void trigger(ScheduleJobInfo scheduleJobInfo);
}
