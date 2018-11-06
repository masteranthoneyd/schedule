package com.yangbingdong.schedule.domain.service;

import com.yangbingdong.schedule.domain.root.ScheduleJobInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ybd
 * @date 18-10-31
 * @contact yangbingdong1994@gmail.com
 */
@Component
@Slf4j
public class SomeService {

	public void realJobLogic(ScheduleJobInfo scheduleJobInfoId) {
		log.info("执行真正的业务代码: {}", scheduleJobInfoId);
	}
}
