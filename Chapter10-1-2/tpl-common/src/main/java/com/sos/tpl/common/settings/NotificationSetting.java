package com.sos.tpl.common.settings;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="notification.sendSecurityCode")
public class NotificationSetting {
	
	private static NotificationSetting NOTIFICATION_SETTING;

	private String templete;
	
	@PostConstruct
	private void init() {
		NOTIFICATION_SETTING = this;
	}
	
	public static NotificationSetting instance() {
		return NOTIFICATION_SETTING;
	}

	public String getTemplete() {
		return templete;
	}

	public void setTemplete(String templete) {
		this.templete = templete;
	}
}
