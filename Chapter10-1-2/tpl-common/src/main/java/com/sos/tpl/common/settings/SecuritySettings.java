package com.sos.tpl.common.settings;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security")
public class SecuritySettings extends SecurityProperties {
	private static  SecuritySettings SECURITY_SETTINGS;
	public static final String ROLE_ADMIN="ROLE_ADMIN";
	public static final String ROLE_USER="ROLE_USER";
	public static final String DENY_ON_READ_ONLY_ENV = "denyOnReadOnlyEnv()";
	public static final String SODW_TYPE_USER="USER";
	
	@PostConstruct
	private void init(){
		SECURITY_SETTINGS = this;
	}
	
	public static SecuritySettings instance(){
		return SECURITY_SETTINGS;
	}
	private String rememberMeParameter;
	private String rememberMeCookie;
	private String rememberTokenKey;
	private String firstLoginUrl;
	private String defaultTargetUrl;
	private int tokenValiditySeconds;
	private String[]permitted;
	public String getRememberMeParameter() {
		return rememberMeParameter;
	}

	public void setRememberMeParameter(String rememberMeParameter) {
		this.rememberMeParameter = rememberMeParameter;
	}

	public String getRememberMeCookie() {
		return rememberMeCookie;
	}

	public void setRememberMeCookie(String rememberMeCookie) {
		this.rememberMeCookie = rememberMeCookie;
	}

	public int getTokenValiditySeconds() {
		return tokenValiditySeconds;
	}

	public void setTokenValiditySeconds(int tokenValiditySeconds) {
		this.tokenValiditySeconds = tokenValiditySeconds;
	}

	public  String getRememberTokenKey() {
		return this.rememberTokenKey;
	}

	public void setRememberTokenKey(String rememberTokenKey) {
		this.rememberTokenKey = rememberTokenKey;
	}

	public String getFirstLoginUrl() {
		return firstLoginUrl;
	}

	public void setFirstLoginUrl(String firstLoginUrl) {
		this.firstLoginUrl = firstLoginUrl;
	}

	public String getDefaultTargetUrl() {
		return defaultTargetUrl;
	}

	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}

	public String[] getPermitted() {
		return permitted;
	}

	public void setPermitted(String[] permitted) {
		this.permitted = permitted;
	}


}
