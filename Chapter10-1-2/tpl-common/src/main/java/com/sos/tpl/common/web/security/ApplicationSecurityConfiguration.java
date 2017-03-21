package com.sos.tpl.common.web.security;

import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.sos.tpl.common.web.security.handler.WebSecurityExpressionHandler;
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true)
public class ApplicationSecurityConfiguration extends
		GlobalMethodSecurityConfiguration {
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		WebSecurityExpressionHandler wseh = new WebSecurityExpressionHandler();
		return wseh;
	}

}
