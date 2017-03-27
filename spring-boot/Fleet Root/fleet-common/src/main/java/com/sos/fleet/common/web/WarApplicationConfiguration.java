package com.sos.fleet.common.web;

import java.util.Locale;

import javax.servlet.DispatcherType;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.sos.fleet.common.filter.ApplicationFilter;


public abstract class WarApplicationConfiguration extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(getClass());
	}
	
	@Bean
	@ConditionalOnClass(name="javax.servlet.DispatcherType")
	@ConditionalOnBean(ApplicationFilter.class)
	/**
	 * Support Servlet 3.0
	 * @return FilterRegistrationBean of Spring for servlet 3.0.
	 */
    public FilterRegistrationBean someFilterRegistration(
    		@Qualifier("sosApplicationFilter")ApplicationFilter applicationFilter
    		) throws ClassNotFoundException, LinkageError {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(applicationFilter);
        registration.addUrlPatterns("/*");
        registration.setName("sosApplicationFilter");
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        return registration;
    }

	private long parseSize(String size) {
		Assert.hasLength(size, "Size must not be empty");
		size = size.toUpperCase();
		if (size.endsWith("KB")) {
			return Long.valueOf(size.substring(0, size.length() - 2)) * 1024;
		}
		if (size.endsWith("MB")) {
			return Long.valueOf(size.substring(0, size.length() - 2)) * 1024 * 1024;
		}
		return Long.valueOf(size);
	}
	
	@Bean
//	@ConditionalOnMissingClass(name="javax.servlet.MultipartConfigElement")
    public CommonsMultipartResolver multipartResolver(@Value("${multipart.max-file-size:}")String maxFileSize){
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setDefaultEncoding("UTF-8");
		cmr.setMaxUploadSize(parseSize(maxFileSize));
        return cmr;
    }
	
	
	/**
	 * Browser Language is not zh_CN which not found language messages.
	 * @return
	 */
	
	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener(){
			private final String REQUEST_ATTRIBUTES_ATTRIBUTE =
					RequestContextListener.class.getName() + ".REQUEST_ATTRIBUTES";
			@Override
			public void requestInitialized(ServletRequestEvent requestEvent) {
				if (!(requestEvent.getServletRequest() instanceof HttpServletRequest)) {
					throw new IllegalArgumentException(
							"Request is not an HttpServletRequest: " + requestEvent.getServletRequest());
				}
				HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
				ServletRequestAttributes attributes = new ServletRequestAttributes(request);
				request.setAttribute(REQUEST_ATTRIBUTES_ATTRIBUTE, attributes);
				LocaleContextHolder.setLocale(Locale.CHINA);
				RequestContextHolder.setRequestAttributes(attributes);
			}
		};
	}
}
