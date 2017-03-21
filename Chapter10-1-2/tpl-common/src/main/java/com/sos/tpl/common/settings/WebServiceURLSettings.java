package com.sos.tpl.common.settings;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "ws")
public class WebServiceURLSettings {
	private String baseUrl;
	private String getSubscriberBySubId;
	private String getSubscriberByEmail;
	private String getSubscriberByVID;
	private String getLocationByVin;
	private String sendSMS;
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

	public String getGetSubscriberBySubId() {
		return getSubscriberBySubId;
	}

	public void setGetSubscriberBySubId(String getSubscriberBySubId) {
		this.getSubscriberBySubId = getSubscriberBySubId;
	}

	public String getGetSubscriberByEmail() {
		return getSubscriberByEmail;
	}

	public void setGetSubscriberByEmail(String getSubscriberByEmail) {
		this.getSubscriberByEmail = getSubscriberByEmail;
	}

	public String getGetSubscriberByVID() {
		return getSubscriberByVID;
	}

	public void setGetSubscriberByVID(String getSubscriberByVID) {
		this.getSubscriberByVID = getSubscriberByVID;
	}
	
	public String getGetLocationByVin() {
		return getLocationByVin;
	}

	public void setGetLocationByVin(String getLocationByVin) {
		this.getLocationByVin = getLocationByVin;
	}

	public String getSendSMS() {
		return sendSMS;
	}

	public void setSendSMS(String sendSMS) {
		this.sendSMS = sendSMS;
	}

}
