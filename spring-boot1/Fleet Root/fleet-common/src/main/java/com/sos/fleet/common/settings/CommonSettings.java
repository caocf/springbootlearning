package com.sos.fleet.common.settings;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="common.resources")
public class CommonSettings {
	private String[] pattern;
	private String[] location;
	private static  CommonSettings COMMON_SETTINGS;
	public String[] getPattern() {
		return pattern;
	}
	@PostConstruct
	private void init(){
		COMMON_SETTINGS = this;
	}
	
	public static CommonSettings instance(){
		return COMMON_SETTINGS;
	}
	public void setPattern(String[] pattern) {
		this.pattern = pattern;
	}
	public String[] getLocation() {
		return location;
	}
	public void setLocation(String[] location) {
		this.location = location;
	}
}
