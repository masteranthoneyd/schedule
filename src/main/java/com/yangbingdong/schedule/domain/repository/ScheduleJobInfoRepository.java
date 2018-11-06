package com.yangbingdong.schedule.domain.repository;

import com.yangbingdong.schedule.domain.root.ScheduleJobInfo;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
@Repository
public class ScheduleJobInfoRepository {
	private static final AtomicLong COUNTER = new AtomicLong(0);
	private static final Map<Long, ScheduleJobInfo> DATA_STORE = new HashMap<>(1 << 4);

	@Nullable
	public ScheduleJobInfo getScheduleJobInfoById(Long id) {
		return DATA_STORE.get(id);
	}

	public void addOrUpdateScheduleJob(ScheduleJobInfo scheduleJobInfo) {
		Long id = scheduleJobInfo.getId();
		if (id == null || id < 1L) {
			scheduleJobInfo.setId(COUNTER.incrementAndGet());
		}
		DATA_STORE.put(scheduleJobInfo.getId(), scheduleJobInfo);
	}

	public Map<Long, ScheduleJobInfo> getAll() {
		return DATA_STORE;
	}
}
