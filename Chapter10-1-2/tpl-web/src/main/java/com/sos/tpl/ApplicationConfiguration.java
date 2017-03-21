package com.sos.tpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.WebApplicationInitializer;

import com.sos.tpl.common.web.WarApplicationConfiguration;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.sos.tpl")
@EntityScan(basePackages = "com.sos.tpl")
public class ApplicationConfiguration extends WarApplicationConfiguration
		implements WebApplicationInitializer {
	static Log log = LogFactory.getLog(ApplicationConfiguration.class);
	public static void main(String[] args) {
		SpringApplication.run(
				ApplicationConfiguration.class, args);
		/*if (log.isDebugEnabled()) {
			log.debug("Let's inspect the beans provided by Spring Boot start>>>>>>>>>>>>>");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				log.debug(beanName);
			}
			log.debug("<<<<<<<<<<<<End.");
		}*/
	}
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//	    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//	    MimeMappings mm = new MimeMappings(MimeMappings.DEFAULT);
//	    mm.add("ico", "image/x-icon");
//	    tomcat.setMimeMappings(mm);
//	    return tomcat;
//	}
}
