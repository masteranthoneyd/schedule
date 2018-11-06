package com.yangbingdong.schedule.domain.root;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * @author ybd
 * @date 18-10-31
 * @contact yangbingdong1994@gmail.com
 */
public enum ScheduleJobStatusEnum {
	/**
	 * 待执行
	 */
	PENDING(0),
	/**
	 * 已执行
	 */
	EXECUTED(1),
	/**
	 * 已取消
	 */
	CANCELED(2),
	/**
	 * 异常
	 */
	ERROR(3);

	private int code;

	private static final Map<Byte, ScheduleJobStatusEnum> MAP;

	static {
		MAP = Stream.of(ScheduleJobStatusEnum.values())
					.collect(toMap(ScheduleJobStatusEnum::getCode, identity()));
	}

	ScheduleJobStatusEnum(int code) {
		this.code = code;
	}

	public byte getCode() {
		return (byte) this.code;
	}

	public static ScheduleJobStatusEnum getByCode(Byte code) {
		return MAP.get(code);
	}
}
