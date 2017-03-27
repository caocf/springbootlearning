package com.sos.fleet.common.settings;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="jasypt")
@Configuration
public class JasyptSettings {
	private static JasyptSettings JASYPT_SETTINGS;
	private String algorithm;
	private String key;
	@PostConstruct
	private void init() {
		JASYPT_SETTINGS = this;
	}

	public static JasyptSettings instance() {

		return JASYPT_SETTINGS;
	}
	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
