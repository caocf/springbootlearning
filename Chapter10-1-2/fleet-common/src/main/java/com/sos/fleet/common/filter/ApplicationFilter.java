package com.sos.fleet.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CompositeFilter;

import com.sos.fleet.common.filter.base.FilterPathMatcher;
import com.sos.fleet.common.filter.base.PathMatcherFilter;
@Component("sosApplicationFilter")
@ConditionalOnProperty(prefix = "filter.ApplicationFilter", name = "enable", havingValue = "true", matchIfMissing = true)
public class ApplicationFilter extends CompositeFilter {
	Log log = LogFactory.getLog(getClass());
	@Autowired
	ApplicationContext applicationContext;
	protected List<PathMatcherFilter> filters;

	@Autowired
	FilterPathMatcher pathMatcher;
	@PostConstruct
	public void init() {
		Map<String, PathMatcherFilter> filterMap = applicationContext
				.getBeansOfType(PathMatcherFilter.class);
		Collections.sort(this.filters = new ArrayList<PathMatcherFilter>(
				filterMap.values()), new Comparator<PathMatcherFilter>() {
			@Override
			public int compare(PathMatcherFilter o1, PathMatcherFilter o2) {
				return o1.getOrder() - o2.getOrder();
			}
		});
		((ArrayList<?>) this.filters).trimToSize();
		setFilters(this.filters);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		/*for (Filter filter : this.filters) {
			filter.init(config);
		}*/
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		new VirtualFilterChain(chain, this.filters, pathMatcher).doFilter(
				request, response);
	}

	private static class VirtualFilterChain implements FilterChain {

		private final FilterChain originalChain;
		FilterPathMatcher pathMatcher;
		private final List<? extends PathMatcherFilter> additionalFilters;

		private int currentPosition = 0;

		public VirtualFilterChain(FilterChain chain,
				List<? extends PathMatcherFilter> additionalFilters,
				FilterPathMatcher pathMatcher) {
			this.originalChain = chain;
			this.additionalFilters = additionalFilters;
			this.pathMatcher = pathMatcher;
		}

		@Override
		public void doFilter(final ServletRequest request,
				final ServletResponse response) throws IOException,
				ServletException {
			Filter filter = findMatcherFilter(request, response);
			if (filter == null) {
				this.originalChain.doFilter(request, response);
			} else {
				filter.doFilter(request, response, this);
			}
			/*
			 * if (this.currentPosition == this.additionalFilters.size()) {
			 * this.originalChain.doFilter(request, response); }else {
			 * 
			 * Filter nextFilter =
			 * this.additionalFilters.get(this.currentPosition);
			 * nextFilter.doFilter(request, response, this);
			 * this.currentPosition++; }
			 */
		}
		public Filter findMatcherFilter(final ServletRequest request,
				final ServletResponse response) {
			for (this.currentPosition++; this.currentPosition <= this.additionalFilters
					.size(); this.currentPosition++) {
				PathMatcherFilter nextFilter = this.additionalFilters
						.get(this.currentPosition - 1);
				if (pathMatcher.match((HttpServletRequest) request,
						(HttpServletResponse) response, nextFilter)) {
					return nextFilter;
				}
			}
			return null;
		}

	}
}
