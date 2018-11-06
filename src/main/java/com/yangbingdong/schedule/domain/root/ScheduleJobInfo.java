package com.yangbingdong.schedule.domain.root;

import java.util.Date;

/**
 * @author ybd
 * @date 18-10-31
 * @contact yangbingdong1994@gmail.com
 */
public class ScheduleJobInfo {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 活动Id
	 */
	private Long actId;

	/**
	 * 活动类型
	 */
	private Byte actType;

	/**
	 * 需要转变到的状态
	 */
	private Byte eventCode;

	/**
	 * cron表达式
	 */
	private String cron;

	/**
	 * cron表达式对应时间
	 */
	private Date cronTime;

	/**
	 * 对应的任务类
	 */
	private String jobClass;

	/**
	 * 唯一的任务名字
	 */
	private String jobName;

	/**
	 * 是否启用
	 */
	private Boolean enable;

	/**
	 * 该任务已触发次数
	 */
	private Integer fireTimes;

	/**
	 * 每次执行的消耗时长，JSON数组
	 */
	private String consume;

	/**
	 * 上一次执行时间
	 */
	private Date lastExecuteTime;

	/**
	 * 状态 0: 待执行, 1: 已执行, 2: 已取消, 3: 执行异常
	 */
	private Byte status;

	/**
	 * 任务描述
	 */
	private String jobDesc;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public ScheduleJobInfo incrFireTimes() {
		fireTimes++;
		return this;
	}

	public Long getId() {
		return id;
	}

	public ScheduleJobInfo setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getActId() {
		return actId;
	}

	public ScheduleJobInfo setActId(Long actId) {
		this.actId = actId;
		return this;
	}

	public Byte getActType() {
		return actType;
	}

	public ScheduleJobInfo setActType(Byte actType) {
		this.actType = actType;
		return this;
	}

	public Byte getEventCode() {
		return eventCode;
	}

	public ScheduleJobInfo setEventCode(Byte eventCode) {
		this.eventCode = eventCode;
		return this;
	}

	public String getCron() {
		return cron;
	}

	public ScheduleJobInfo setCron(String cron) {
		this.cron = cron;
		return this;
	}

	public Date getCronTime() {
		return cronTime;
	}

	public ScheduleJobInfo setCronTime(Date cronTime) {
		this.cronTime = cronTime;
		return this;
	}

	public String getJobClass() {
		return jobClass;
	}

	public ScheduleJobInfo setJobClass(String jobClass) {
		this.jobClass = jobClass;
		return this;
	}

	public String getJobName() {
		return jobName;
	}

	public ScheduleJobInfo setJobName(String jobName) {
		this.jobName = jobName;
		return this;
	}

	public Boolean getEnable() {
		return enable;
	}

	public ScheduleJobInfo setEnable(Boolean enable) {
		this.enable = enable;
		return this;
	}

	public Integer getFireTimes() {
		return fireTimes;
	}

	public ScheduleJobInfo setFireTimes(Integer fireTimes) {
		this.fireTimes = fireTimes;
		return this;
	}

	public String getConsume() {
		return consume;
	}

	public ScheduleJobInfo setConsume(String consume) {
		this.consume = consume;
		return this;
	}

	public Date getLastExecuteTime() {
		return lastExecuteTime;
	}

	public ScheduleJobInfo setLastExecuteTime(Date lastExecuteTime) {
		this.lastExecuteTime = lastExecuteTime;
		return this;
	}

	public Byte getStatus() {
		return status;
	}

	public ScheduleJobInfo setStatus(Byte status) {
		this.status = status;
		return this;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public ScheduleJobInfo setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public ScheduleJobInfo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	@Override
	public String toString() {
		return "ScheduleJobInfo{" +
				"id=" + id +
				", actId=" + actId +
				", actType=" + actType +
				", eventCode=" + eventCode +
				", cron='" + cron + '\'' +
				", cronTime=" + cronTime +
				", jobClass='" + jobClass + '\'' +
				", jobName='" + jobName + '\'' +
				", enable=" + enable +
				", fireTimes=" + fireTimes +
				", consume='" + consume + '\'' +
				", lastExecuteTime=" + lastExecuteTime +
				", status=" + status +
				", jobDesc='" + jobDesc + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
