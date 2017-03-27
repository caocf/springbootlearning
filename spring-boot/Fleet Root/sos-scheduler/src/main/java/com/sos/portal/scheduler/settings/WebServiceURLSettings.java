package com.sos.portal.scheduler.settings;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "ws")
public class WebServiceURLSettings {
	private String baseUrl;
	public String getBaseUrl() {
		return baseUrl;
	}
	private static WebServiceURLSettings SETTINGS;
	@PostConstruct
	public void init(){
		SETTINGS = this;
	}
	
	public static WebServiceURLSettings instance(){
		return SETTINGS;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public static String getWSUrl(String... path) {
		StringBuffer sb = new StringBuffer(SETTINGS.baseUrl);
		for (int i = 0; i < path.length; i++) {
			sb.append(path[i]);
		}
		return sb.toString();
	}


}
