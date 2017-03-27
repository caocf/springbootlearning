package com.sos.portal.scheduler.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "scheduler")
public class SchedulerSettings {
	private int batchCount;
	private int rerunTimes;
	private boolean testDateEnable;
	private String testFromDate;
	private String testToDate;
	private boolean concurrent;
	public int getBatchCount() {
		return batchCount;
	}
	public void setBatchCount(int batchCount) {
		this.batchCount = batchCount;
	}
	public int getRerunTimes() {
		return rerunTimes;
	}
	public void setRerunTimes(int rerunTimes) {
		this.rerunTimes = rerunTimes;
	}
	public boolean isTestDateEnable() {
		return testDateEnable;
	}
	public void setTestDateEnable(boolean testDateEnable) {
		this.testDateEnable = testDateEnable;
	}
	public String getTestFromDate() {
		return testFromDate;
	}
	public void setTestFromDate(String testFromDate) {
		this.testFromDate = testFromDate;
	}
	public String getTestToDate() {
		return testToDate;
	}
	public void setTestToDate(String testToDate) {
		this.testToDate = testToDate;
	}
	public boolean isConcurrent() {
		return concurrent;
	}
	public void setConcurrent(boolean concurrent) {
		this.concurrent = concurrent;
	}
	
}
