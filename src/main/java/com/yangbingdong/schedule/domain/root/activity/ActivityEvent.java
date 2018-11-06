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
public enum ActivityEvent {
	/**
	 *
	 */
	START_EVENT(1), PREPARE_START_EVENT(2), END_EVENT(3);

	private static final Map<Byte, ActivityEvent> MAP;

	static {
		MAP = Stream.of(ActivityEvent.values())
					.collect(Collectors.toMap(ActivityEvent::getCode, Function.identity()));
	}

	private int code;

	ActivityEvent(int code) {
		this.code = code;
	}

	public byte getCode() {
		return (byte) code;
	}

	public static ActivityEvent getByCode(Byte code) {
		return MAP.get(code);
	}


}
