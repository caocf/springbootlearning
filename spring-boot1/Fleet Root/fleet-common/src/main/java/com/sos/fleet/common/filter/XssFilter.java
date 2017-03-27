package com.sos.fleet.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.sos.fleet.common.filter.base.OncePerRequestBaseFilter;
import com.sos.fleet.common.settings.XssFilterSettings;

@Component
@ConditionalOnProperty(prefix="filter.XssFilter",name="enable",havingValue="true",matchIfMissing=true)
public class XssFilter extends OncePerRequestBaseFilter {
	static Log log = LogFactory.getLog(XssFilter.class);
	XssFilterSettings xssFilterSettings;
	public static class XssHttpServletRequestWrapper extends
			HttpServletRequestWrapper {
		public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
			super(servletRequest);
		}

		public String[] getParameterValues(String parameter) {
			String[] values = super.getParameterValues(parameter);
			if (values == null) {
				return null;
			}
			int count = values.length;
			String[] encodedValues = new String[count];
			for (int i = 0; i < count; i++) {
				encodedValues[i] = cleanXSS(values[i]);
			}
			return encodedValues;
		}

		public String getParameter(String parameter) {
			String value = super.getParameter(parameter);
			if (value == null) {
				return null;
			}
			return cleanXSS(value);
		}

		public String getHeader(String name) {
			String value = super.getHeader(name);
			if (value == null)
				return null;
			return cleanXSS(value);
		}

		private String cleanXSS(String value) {
			return StringEscapeUtils.escapeHtml4(value);
		}

	}

	@Override
	public void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		filterChain.doFilter(new XssHttpServletRequestWrapper(request), response);
	}

	@Override
	@Bean(name="xssFilterRegistration")
	@ConditionalOnClass(name="javax.servlet.DispatcherType")
	public FilterRegistrationBean inactiveRegister() {
		return super.inactiveRegister();
	}

	@Autowired
	public void setXssFilterSettings(XssFilterSettings xssFilterSettings) {
		this.filterProperties = this.xssFilterSettings = xssFilterSettings;
	}
}
