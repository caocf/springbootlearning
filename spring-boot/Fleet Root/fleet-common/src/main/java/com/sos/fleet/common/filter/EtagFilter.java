package com.sos.fleet.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.sos.fleet.common.filter.base.PathMatcherFilter;
import com.sos.fleet.common.settings.EtagFilterSettings;

@Component
@ConditionalOnProperty(prefix="filter.EtagFilter",name="enable",havingValue="true",matchIfMissing=true)
public class EtagFilter extends ShallowEtagHeaderFilter implements
		PathMatcherFilter {
	@Autowired
	EtagFilterSettings etagFilterSetting;
	@Override
	public int getOrder() {
		return etagFilterSetting.getOrder();
	}

	@Bean(name="etagFilterRegistration")
	@ConditionalOnClass(name="javax.servlet.DispatcherType")
	public FilterRegistrationBean inactiveRegister() {
		FilterRegistrationBean filter =	 new FilterRegistrationBean();
		filter.setFilter(this);
		filter.setName("etagFilterRegistration");
		filter.setEnabled(false);
		this.setBeanName("etagFilter");
		return filter;
	}
	@Override
	public String[] getIncludePattern() {
		return etagFilterSetting.getIncludePattern();
	}

	@Override
	public String[] getExcludePattern() {
		return etagFilterSetting.getExcludePattern();
	}

	@Override
	public boolean matchPerRequest(HttpServletRequest request,
			HttpServletResponse response) {
		return true;
	}

}
