package com.sos.tpl.common.weblogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.sos.tpl.common.web.validator.resolver.OpenTraversableResolver;

@Configuration
@ConditionalOnProperty("weblogic.home")
public class WeblogicConfiguration {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public Validator configurationPropertiesValidator(){
		 LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setApplicationContext(applicationContext);
		validator.setTraversableResolver(new OpenTraversableResolver());
		validator.afterPropertiesSet();
		return validator;
	}
}
