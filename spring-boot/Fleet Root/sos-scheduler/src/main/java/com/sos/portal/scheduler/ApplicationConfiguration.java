package com.sos.portal.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.web.WebApplicationInitializer;

import com.sos.portal.scheduler.web.WarApplicationConfiguration;

@SpringBootApplication(exclude=FreeMarkerAutoConfiguration.class)
public class ApplicationConfiguration extends WarApplicationConfiguration
		implements WebApplicationInitializer {
	static Log log = LogFactory.getLog(ApplicationConfiguration.class);
	public static void main(String[] args) {
		SpringApplication.run(
				ApplicationConfiguration.class, args);
	}
}
