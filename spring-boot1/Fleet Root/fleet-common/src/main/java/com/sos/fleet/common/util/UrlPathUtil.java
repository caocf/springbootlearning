package com.sos.fleet.common.util;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UrlPathHelper;

@Component
public  class UrlPathUtil {
	
	private static UrlPathUtil urlPathUtil;
	@Autowired
	private UrlPathHelper urlPathHelper;
	@Value("${spring.view.prefix:}")
	private String webPrefix;
	@Value("${spring.view.suffix:}")
	private String webSuffix;
	@PostConstruct
	public void init(){
		urlPathUtil = this;
	}
	

	public static void setAlwaysUseFullPath(boolean alwaysUseFullPath) {
		urlPathUtil.urlPathHelper.setAlwaysUseFullPath(alwaysUseFullPath);
	}

	public static void setUrlDecode(boolean urlDecode) {
		urlPathUtil.urlPathHelper.setUrlDecode(urlDecode);
	}

	public static void setRemoveSemicolonContent(boolean removeSemicolonContent) {
		urlPathUtil.urlPathHelper.setRemoveSemicolonContent(removeSemicolonContent);
	}

	public static boolean shouldRemoveSemicolonContent() {
		return urlPathUtil.urlPathHelper.shouldRemoveSemicolonContent();
	}

	public static void setDefaultEncoding(String defaultEncoding) {
		urlPathUtil.urlPathHelper.setDefaultEncoding(defaultEncoding);
	}

	public static String getLookupPathForRequest(HttpServletRequest request) {
		return urlPathUtil.urlPathHelper.getLookupPathForRequest(request);
	}

	public static String getPathWithinServletMapping(HttpServletRequest request) {
		return urlPathUtil.urlPathHelper.getPathWithinServletMapping(request);
	}

	public static String getPathWithinApplication(HttpServletRequest request) {
		return urlPathUtil.urlPathHelper.getPathWithinApplication(request);
	}

	public static String getRequestUri(HttpServletRequest request) {
		return urlPathUtil.urlPathHelper.getRequestUri(request);
	}

	public static String getContextPath(HttpServletRequest request) {
		return urlPathUtil.urlPathHelper.getContextPath(request);
	}

	public static String getServletPath(HttpServletRequest request) {
		return urlPathUtil.urlPathHelper.getServletPath(request);
	}

	public static String getOriginatingRequestUri(HttpServletRequest request) {
		return urlPathUtil.urlPathHelper.getOriginatingRequestUri(request);
	}

	public static String getOriginatingContextPath(HttpServletRequest request) {
		return urlPathUtil.urlPathHelper.getOriginatingContextPath(request);
	}

	public static String getOriginatingServletPath(HttpServletRequest request) {
		return urlPathUtil.urlPathHelper.getOriginatingServletPath(request);
	}

	public static String getOriginatingQueryString(HttpServletRequest request) {
		return urlPathUtil.urlPathHelper.getOriginatingQueryString(request);
	}

	public static String decodeRequestString(HttpServletRequest request, String source) {
		return urlPathUtil.urlPathHelper.decodeRequestString(request, source);
	}

	public static String removeSemicolonContent(String requestUri) {
		return urlPathUtil.urlPathHelper.removeSemicolonContent(requestUri);
	}

	public static Map<String, String> decodePathVariables(HttpServletRequest request,
			Map<String, String> vars) {
		return urlPathUtil.urlPathHelper.decodePathVariables(request, vars);
	}

	public static MultiValueMap<String, String> decodeMatrixVariables(
			HttpServletRequest request, MultiValueMap<String, String> vars) {
		return urlPathUtil.urlPathHelper.decodeMatrixVariables(request, vars);
	}
	
	public static String getServletPathKey(HttpServletRequest request){
		String servletPath = getServletPath(request);
		if(servletPath.endsWith(urlPathUtil.webSuffix)){
			servletPath =	servletPath.substring(0,servletPath.length()-urlPathUtil.webSuffix.length());
		}
		if(servletPath.startsWith("/")){
			servletPath = servletPath.replaceFirst("/+", "");
		}
		return servletPath.replaceAll("/+", ".");
	}
	public static String getServletName(HttpServletRequest request){
		String servletPath = getServletPathKey(request);
		return servletPath.substring(servletPath.lastIndexOf(".")+1);
	}
	public static String getFullServletName(HttpServletRequest request){
		String servletPath = getServletPath(request);
		if(servletPath.startsWith(urlPathUtil.webPrefix)){
			servletPath = servletPath.substring(urlPathUtil.webPrefix.length());
		}
		if(servletPath.startsWith("/")){
			servletPath = servletPath.replaceFirst("/+", "");
		}
		if(servletPath.endsWith(urlPathUtil.webSuffix)){
			servletPath = servletPath.substring(0,servletPath.length()-urlPathUtil.webSuffix.length());
		}
		return servletPath.replaceAll("/+", ".");
	}
}
