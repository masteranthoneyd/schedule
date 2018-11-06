package com.yangbingdong.schedule.job;

import com.yangbingdong.schedule.domain.repository.ScheduleJobInfoRepository;
import com.yangbingdong.schedule.domain.root.ScheduleJobInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import javax.annotation.Resource;
import java.util.Date;

import static com.yangbingdong.schedule.domain.root.ScheduleJobStatusEnum.ERROR;
import static com.yangbingdong.schedule.domain.root.ScheduleJobStatusEnum.EXECUTED;

/**
 * @author ybd
 * @date 18-10-31
 * @contact yangbingdong1994@gmail.com
 */
@Slf4j
public abstract class BaseDynamicScheduleJob implements Job {
	public static final String SCHEDULE_JOB_ID_KEY = ScheduleJobInfo.class.getSimpleName() + "-Id";

	@Resource
	protected ScheduleJobInfoRepository repository;

	@Override
	public void execute(JobExecutionContext context) {
		Long id = (Long) context.getMergedJobDataMap().get(SCHEDULE_JOB_ID_KEY);
		ScheduleJobInfo scheduleJobInfo = repository.getScheduleJobInfoById(id);
		if (scheduleJobInfo == null) {
			log.error("ScheduleJobInfo is null: id = {}", id);
			return;
		}
		if (!validScheduleJobInfo(scheduleJobInfo)) {
			return;
		}
		long start = System.currentTimeMillis();
		try {
			log.info("Executing schedule job: id = {}", id);
			scheduleJobInfo.setLastExecuteTime(new Date(start));
			executeInternal(scheduleJobInfo);
			scheduleJobInfo.setStatus(EXECUTED.getCode());
		} catch (Exception e) {
			log.error("Occur an exception when executeInternal, id = " + id, e);
			scheduleJobInfo.setStatus(ERROR.getCode());
		}
		scheduleJobInfo.incrFireTimes()
					   .setConsume(String.valueOf(System.currentTimeMillis() - start));
		repository.addOrUpdateScheduleJob(scheduleJobInfo);
		log.info("Complete execute schedule job: id = {}", id);
	}

	private boolean validScheduleJobInfo(ScheduleJobInfo scheduleJobInfo) {
		Long id = scheduleJobInfo.getId();
		if (!scheduleJobInfo.getEnable()) {
			log.error("Schedule job not enable: id = ", id);
			return false;
		}
		if (scheduleJobInfo.getStatus().equals(EXECUTED.getCode())) {
			log.error("Schedule job has been executed, id = " + id);
			return false;
		}
		return true;
	}

	protected abstract void executeInternal(ScheduleJobInfo scheduleJobInfoId) throws Exception;
}
