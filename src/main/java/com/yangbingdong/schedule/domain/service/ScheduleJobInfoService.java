package com.yangbingdong.schedule.domain.service;

import com.yangbingdong.schedule.core.ScheduleJobOperations;
import com.yangbingdong.schedule.domain.repository.ScheduleJobInfoRepository;
import com.yangbingdong.schedule.domain.root.ScheduleJobInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
@Service
public class ScheduleJobInfoService {

	@Resource
	private ScheduleJobInfoRepository repository;

	@Resource
	private ScheduleJobOperations scheduleJobOperations;

	public void addOrUpdateScheduleJob(ScheduleJobInfo scheduleJobInfo) {
		repository.addOrUpdateScheduleJob(scheduleJobInfo);
		scheduleJobOperations.addOrUpdateScheduleJob(scheduleJobInfo);
	}
}
