package com.sos.fleet.common.settings;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "amap")
@Configuration
public class AmapSettings {
	private String javascriptUrl,cssUrl;
	
	private static AmapSettings AMAP_SETTINGS;
	@PostConstruct
	private void init(){
		AMAP_SETTINGS = this;
	}
	
	public static AmapSettings instance(){
		return AMAP_SETTINGS;
	}
	public String getJavascriptUrl() {
		return javascriptUrl;
	}

	public void setJavascriptUrl(String url) {
		this.javascriptUrl = url;
	}

	public String getCssUrl() {
		return cssUrl;
	}

	public void setCssUrl(String cssUrl) {
		this.cssUrl = cssUrl;
	}
	
}
