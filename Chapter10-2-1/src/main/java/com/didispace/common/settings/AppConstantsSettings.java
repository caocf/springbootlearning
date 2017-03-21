package com.didispace.common.settings;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "appconstants")
@Configuration
public class AppConstantsSettings {
	public static final String ATTRIBUTE_KEY="appconstants";
	private static AppConstantsSettings APPCONSTANTS_SETTINGS;
	
	private String adminPath,frontPath;
	
	@PostConstruct
	private void init(){
		APPCONSTANTS_SETTINGS = this;
	}
	
	public static AppConstantsSettings instance(){
		return APPCONSTANTS_SETTINGS;
	}

	public String getAdminPath() {
		return adminPath;
	}

	public void setAdminPath(String adminPath) {
		this.adminPath = adminPath;
	}

	public String getFrontPath() {
		return frontPath;
	}

	public void setFrontPath(String frontPath) {
		this.frontPath = frontPath;
	}
	
	 
}
