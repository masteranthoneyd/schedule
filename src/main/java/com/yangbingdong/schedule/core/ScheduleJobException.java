package com.yangbingdong.schedule.core;

/**
 * @author ybd
 * @date 18-10-31
 * @contact yangbingdong1994@gmail.com
 */
public class ScheduleJobException extends RuntimeException {
	private static final long serialVersionUID = 6673970486111994384L;

	public ScheduleJobException() {
		super();
	}

	public ScheduleJobException(String message) {
		super(message);
	}

	public ScheduleJobException(String message, Throwable cause) {
		super(message, cause);
	}

	public ScheduleJobException(Throwable cause) {
		super(cause);
	}

	protected ScheduleJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
