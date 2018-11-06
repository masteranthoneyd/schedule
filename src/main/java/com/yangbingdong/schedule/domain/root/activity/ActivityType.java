package com.yangbingdong.schedule.domain.root.activity;

/**
 * @author ybd
 * @date 18-11-1
 * @contact yangbingdong1994@gmail.com
 */
public enum ActivityType {
	/**
	 *
	 */
	COUPON_ACTIVITY(1);
	private int code;

	ActivityType(int code) {
		this.code = code;
	}

	public byte getCode() {
		return (byte) code;
	}

}
