package com.sos.fleet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.WebApplicationInitializer;

import com.sos.fleet.common.web.WarApplicationConfiguration;

@SpringBootApplication
/*
 * @EnableAutoConfiguration
 * If servlet 2.5 container , please set exclude={SecurityAutoConfiguration.class}.
 * If servlet 3.0 please remove this annotation.
 */
public class ApplicationConfiguration extends WarApplicationConfiguration
		implements WebApplicationInitializer {
	static Log log = LogFactory.getLog(ApplicationConfiguration.class);

	public static void main(String[] args) {
		SpringApplication.run(
				ApplicationConfiguration.class, args);
	}
	
}
