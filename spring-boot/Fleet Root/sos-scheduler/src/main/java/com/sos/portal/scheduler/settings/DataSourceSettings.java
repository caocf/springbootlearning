package com.sos.portal.scheduler.settings;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DataSourceSettings {
	
	public static class Jndi{
		private String id;
		private String name;
		private boolean isDefault;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isDefault() {
			return isDefault;
		}
		public void setDefault(boolean isDefault) {
			this.isDefault = isDefault;
		}
		
	}
	
	private List<Jndi> jndis;

	public List<Jndi> getJndis() {
		return jndis;
	}

	public void setJndis(List<Jndi> jndis) {
		this.jndis = jndis;
	}
}
