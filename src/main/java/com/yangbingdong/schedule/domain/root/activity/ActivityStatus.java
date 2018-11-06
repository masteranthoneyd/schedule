package com.yangbingdong.schedule.domain.root.activity;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
public enum ActivityStatus {
	/**
	 *
	 */
	PENDING(0), PREPARE_START(1), STARTED(2), PREPARE_END(3), ENDED(4), CANCEL(5);

	private int code;

	private static final Map<Byte, ActivityStatus> MAP;

	static {
		MAP = Stream.of(ActivityStatus.values())
					.collect(Collectors.toMap(ActivityStatus::getCode, Function.identity()));
	}

	ActivityStatus(int code) {
		this.code = code;
	}

	public byte getCode() {
		return (byte) code;
	}

	public static ActivityStatus getByCode(Byte code) {
		return MAP.get(code);
	}
}
