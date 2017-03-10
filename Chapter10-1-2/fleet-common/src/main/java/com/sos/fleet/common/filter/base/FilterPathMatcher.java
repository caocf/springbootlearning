package com.sos.fleet.common.filter.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;

import com.sos.fleet.common.util.UrlPathUtil;

@Component
public class FilterPathMatcher{
	@Autowired
	PathMatcher pathMatcher;
	
	public boolean match(HttpServletRequest request, HttpServletResponse response,PathMatcherFilter filter){
		String [] includePatterns = filter.getIncludePattern();
		String [] excludePatterns = filter.getExcludePattern();
		if(excludePatterns!=null){
			for (int i = 0; i < excludePatterns.length; i++) {
				boolean rs = pathMatcher.match(excludePatterns[i],UrlPathUtil.getServletPath(request));
				if (rs){
					return !rs;
				}
			}
		}
		boolean rs = false;
		if(includePatterns!=null){
			for (int i = 0; i < includePatterns.length; i++) {
				rs = pathMatcher.match(includePatterns[i],UrlPathUtil.getServletPath(request));
				if (rs){
					break;
				}
			}
		}
		return rs&&filter.matchPerRequest(request, response);
	}
	
}
