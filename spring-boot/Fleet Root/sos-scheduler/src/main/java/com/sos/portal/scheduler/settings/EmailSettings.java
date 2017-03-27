package com.sos.portal.scheduler.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email")
public class EmailSettings {
	private boolean sendNoticeEnabled;
	private boolean sendSuccessEnabled;
	private String sendFormAddress;
	private String sendErrorToAddress;
	private String sendNoticeToAddress;
	private String sendSubjectFormat;
	public boolean isSendNoticeEnabled() {
		return sendNoticeEnabled;
	}
	public void setSendNoticeEnabled(boolean sendNoticeEnabled) {
		this.sendNoticeEnabled = sendNoticeEnabled;
	}
	public boolean isSendSuccessEnabled() {
		return sendSuccessEnabled;
	}
	public void setSendSuccessEnabled(boolean sendSuccessEnabled) {
		this.sendSuccessEnabled = sendSuccessEnabled;
	}
	public String getSendFormAddress() {
		return sendFormAddress;
	}
	public void setSendFormAddress(String sendFormAddress) {
		this.sendFormAddress = sendFormAddress;
	}
	public String getSendErrorToAddress() {
		return sendErrorToAddress;
	}
	public void setSendErrorToAddress(String sendErrorToAddress) {
		this.sendErrorToAddress = sendErrorToAddress;
	}
	public String getSendNoticeToAddress() {
		return sendNoticeToAddress;
	}
	public void setSendNoticeToAddress(String sendNoticeToAddress) {
		this.sendNoticeToAddress = sendNoticeToAddress;
	}
	public String getSendSubjectFormat() {
		return sendSubjectFormat;
	}
	public void setSendSubjectFormat(String sendSubjectFormat) {
		this.sendSubjectFormat = sendSubjectFormat;
	}
}
