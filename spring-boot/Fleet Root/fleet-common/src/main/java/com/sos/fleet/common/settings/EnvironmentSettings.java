package com.sos.fleet.common.settings;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix="env")
public class EnvironmentSettings {
	private String name;
	private String contextName;
	private static   EnvironmentSettings ENVIRONMENT_SETTINGS;
	public static boolean IS_EFO;
	public static boolean IS_PROD_OR_EFO;
	public static boolean ENABLE_DREAD_ONLY;
	private boolean enabledReadOnly;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@PostConstruct
	private void init(){
		ENVIRONMENT_SETTINGS = this;
		IS_EFO  = this.name.toLowerCase().contains("efo");
		IS_PROD_OR_EFO = this.name.toLowerCase().contains("efo") || this.name.toLowerCase().contains("prod");
		ENABLE_DREAD_ONLY =  this.enabledReadOnly;
	}
	
	public static EnvironmentSettings instance(){
		return ENVIRONMENT_SETTINGS;
	}

	public boolean isEnabledReadOnly() {
		return enabledReadOnly;
	}

	public void setEnabledReadOnly(boolean enabledReadOnly) {
		this.enabledReadOnly = enabledReadOnly;
	}

	protected String getContextName() {
		return contextName;
	}

	protected void setContextName(String contextName) {
		this.contextName = contextName;
	}

}
