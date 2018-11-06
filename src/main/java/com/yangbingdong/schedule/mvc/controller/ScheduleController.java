package com.yangbingdong.schedule.mvc.controller;

import com.yangbingdong.schedule.domain.repository.ScheduleJobInfoRepository;
import com.yangbingdong.schedule.domain.root.ScheduleJobInfo;
import com.yangbingdong.schedule.domain.root.activity.ActivityEvent;
import com.yangbingdong.schedule.domain.root.activity.ActivityType;
import com.yangbingdong.schedule.domain.service.ScheduleJobInfoService;
import com.yangbingdong.schedule.job.ActivityScheduleJob;
import com.yangbingdong.schedule.util.CronUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import static com.yangbingdong.schedule.domain.root.ScheduleJobStatusEnum.PENDING;
import static com.yangbingdong.schedule.util.CronUtils.getDateByCron;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
@RestController
public class ScheduleController {

	@Resource
	private ScheduleJobInfoService service;

	@Resource
	private ScheduleJobInfoRepository repository;

	@PostMapping("/{actId}")
	public String addScheduleJob(@PathVariable String actId) {
		ScheduleJobInfo scheduleJobInfo = buildScheduleJobInfo(actId, null);
		ScheduleJobInfo scheduleJobInfoEnd = buildScheduleJobInfo(actId, 15L).setEventCode(ActivityEvent.END_EVENT.getCode()).setJobName("ACT-END" + actId);
		service.addOrUpdateScheduleJob(scheduleJobInfo);
		service.addOrUpdateScheduleJob(scheduleJobInfoEnd);
		return "OK";
	}

	@PostMapping("/{actId}/{second}")
	public String addScheduleJobWithSecond(@PathVariable String actId, @PathVariable Long second) {
		ScheduleJobInfo scheduleJobInfo = buildScheduleJobInfo(actId, second);
		service.addOrUpdateScheduleJob(scheduleJobInfo);
		return "OK";
	}

	@GetMapping("/all")
	public Map<Long, ScheduleJobInfo> getAll() {
		return repository.getAll();
	}

	private ScheduleJobInfo buildScheduleJobInfo(String actId, Long second) {
		ScheduleJobInfo jobInfo = new ScheduleJobInfo();
		LocalDateTime dateTime = LocalDateTime.now().plusSeconds(second != null && second > 0 ? second : 10L);
		String cron = CronUtils.getCron(dateTime);
		jobInfo.setActId(Long.valueOf(actId))
			   .setActType(ActivityType.COUPON_ACTIVITY.getCode())
			   .setCreateTime(new Date())
			   .setCron(cron)
			   .setCronTime(getDateByCron(cron))
			   .setEnable(true)
			   .setJobClass(ActivityScheduleJob.class.getName())
			   .setJobDesc("测试")
			   .setJobName("ACT-" + actId)
			   .setStatus(PENDING.getCode())
			   .setEventCode(ActivityEvent.START_EVENT.getCode())
			   .setFireTimes(0);
		return jobInfo;
	}
}
