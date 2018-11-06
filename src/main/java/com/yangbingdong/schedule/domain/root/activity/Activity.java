package com.yangbingdong.schedule.domain.root.activity;

import com.yangbingdong.schedule.fms.core.StateSource;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
@Data
@Accessors(chain = true)
public class Activity implements StateSource<ActivityStatus> {
	private Long actId;
	private Byte actType;
	private Byte status;

	@Override
	public ActivityStatus getState() {
		return ActivityStatus.getByCode(this.status);
	}
}
