package com.sos.fleet.common.filter.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sos.fleet.common.filter.properties.FilterProperties;

/**
 * @author junjunzhu
 *
 */
public abstract class OncePerRequestBaseFilter extends OncePerRequestFilter implements PathMatcherFilter {
	protected FilterProperties filterProperties;
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
		this.setBeanName(this.getClass().getName());
		return filter;
	}
	public String[] getIncludePattern() {
		return filterProperties.getIncludePattern();
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
