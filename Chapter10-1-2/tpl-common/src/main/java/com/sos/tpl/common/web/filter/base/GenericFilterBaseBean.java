package com.sos.tpl.common.web.filter.base;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.web.filter.GenericFilterBean;

import com.sos.tpl.common.web.filter.properties.FilterProperties;

public abstract class GenericFilterBaseBean  extends GenericFilterBean implements PathMatcherFilter{
	
	protected FilterProperties filterProperties;
	
	@Override
	public String[] getIncludePattern() {
		return filterProperties.getIncludePattern();
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException("OncePerRequestFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		doFilterInternal(httpRequest, httpResponse, chain);
	}
	public abstract void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException;
	@Override
	public int getOrder() {
		return filterProperties.getOrder();
	}
	@Override
	public FilterRegistrationBean inactiveRegister() {
		FilterRegistrationBean filter =	 new FilterRegistrationBean();
		filter.setFilter(this);
		filter.setName(this.getClass().getName()+"Register");
		filter.setEnabled(false);
		return filter;
	}
	public String[] getExcludePattern() {
		return filterProperties.getExcludePattern();
	}
	@Override
	public boolean matchPerRequest(HttpServletRequest request,
			HttpServletResponse response) {
		return true;
	}
	public FilterProperties getFilterProperties() {
		return filterProperties;
	}
	public void setFilterProperties(FilterProperties filterProperties) {
		this.filterProperties = filterProperties;
	}
	
}
