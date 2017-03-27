package com.sos.fleet.common.web;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sos.fleet.common.interceptors.FirstLoginInterceptor;
import com.sos.fleet.common.settings.CommonSettings;
import com.sos.fleet.common.settings.SecuritySettings;


@Component
public class ApplicationMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	
	@Autowired
	private CommonSettings commonSettings;
	@Value("${interceptor.firstLoginInterceptor.enable:false}")
	private String enableFirstLogin;
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/access").setViewName("access");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(commonSettings.getPattern()).addResourceLocations(commonSettings.getLocation());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if(BooleanUtils.toBoolean(enableFirstLogin)){
			String[] ignore = SecuritySettings.instance().getIgnored().toArray(new String[SecuritySettings.instance().getIgnored().size()]);
			String[] target = new String[ignore.length+2];
			target[0] = "/login";
			target[1]="/access";
			System.arraycopy(ignore, 0, target, 2, ignore.length);
			registry.addInterceptor(new FirstLoginInterceptor()).addPathPatterns("/**").excludePathPatterns(target);
		}
	}

}
