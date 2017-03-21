package com.sos.tpl.common.web.filter.base;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.core.Ordered;

public interface PathMatcherFilter extends  Filter,Ordered{

	public String[] getIncludePattern();
	
	public String[] getExcludePattern();

	public boolean matchPerRequest(HttpServletRequest request,HttpServletResponse response);
	
	public FilterRegistrationBean inactiveRegister();
	
}
