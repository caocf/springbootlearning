package com.sos.fleet.common.settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.sos.fleet.common.filter.properties.FilterProperties;
import com.sos.fleet.common.util.UrlPathUtil;

@ConfigurationProperties(prefix = "filter.CommonJsFilter")
@Configuration
public class CommonJsSettings extends FilterProperties {

	public static final String SCRIPT_URL = "/common/script";
//	public static final String CHILDREN_PARAMETER = "children";
	public static final String IGNORE_ADD_COMMON_SCRIPT = "IGNORE_ADD_COMMON_SCRIPT";
	public static final String COMMON_SCRIPT_CHILDREN = "COMMON_SCRIPT_CHILDREN";
	private String childrenScripts, appendTo, contentTypes;
	private String[] allowContentTypes;
	private Map<String, String[]> childrenScriptPath;
	private static CommonJsSettings COMMONJSSETTINGS;
	private String childrenPrefix;

	@PostConstruct
	private void init() {
		if(ArrayUtils.isNotEmpty(this.allowContentTypes))
			this.allowContentTypes = getAllowContentTypes(this.contentTypes);
		if(StringUtils.hasText(childrenScripts))
			this.childrenScriptPath = getScriptPath(this.childrenScripts);
		COMMONJSSETTINGS = this;

	}

	private static String[] getAllowContentTypes(String contentTypes) {
		String[] items = contentTypes.split(";");
		for (int i = 0; i < items.length; i++) {
			items[i] = items[i].trim();
		}
		return items;
	}

	private static Map<String, String[]> getScriptPath(String childrenScripts) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		String[] children = childrenScripts.split(";");
		children: for (int i = 0; i < children.length; i++) {
			int index = children[i].indexOf(":");
			if (index < 0 || index == children[i].length() - 1) {
				continue children;
			}
			String key = children[i].substring(0, index);
			String[] items = children[i].substring(index + 1).split(",");
			for (int j = 0; j < items.length; j++) {
				items[j] = items[j].trim();
			}
			map.put(key, items);
		}
		return Collections.unmodifiableMap(map);
	}

	public static CommonJsSettings instance() {
		return COMMONJSSETTINGS;
	}

	public String getChildrenScripts() {
		return childrenScripts;
	}

	public void setChildrenScripts(String childrenScripts) {
		this.childrenScripts = childrenScripts;
	}

	public String getAppendTo() {
		return appendTo;
	}

	public void setAppendTo(String appendTo) {
		this.appendTo = appendTo;
	}

	public Map<String, String[]> getChildrenScriptPath() {
		return childrenScriptPath;
	}

	public void setChildrenScriptPath(Map<String, String[]> childrenScriptPath) {
		this.childrenScriptPath = childrenScriptPath;
	}

	public String getContentTypes() {
		return contentTypes;
	}

	public void setContentTypes(String contentTypes) {
		this.contentTypes = contentTypes;
	}

	public String[] getAllowContentTypes() {
		return allowContentTypes;
	}

	public void setAllowContentTypes(String[] allowContentTypes) {
		this.allowContentTypes = allowContentTypes;
	}

	public static boolean matchContentType(String contentType) {
		if (contentType == null)
			return false;
		for (int i = 0; i < COMMONJSSETTINGS.allowContentTypes.length; i++) {
			String allowContentType = COMMONJSSETTINGS.allowContentTypes[i];
			if (contentType.toLowerCase().indexOf(
					allowContentType.toLowerCase()) > -1) {
				return true;
			}
		}
		return false;
	}

	public static void ignoreAddCommonScript(HttpServletRequest request) {
		request.setAttribute(IGNORE_ADD_COMMON_SCRIPT, IGNORE_ADD_COMMON_SCRIPT);
	}

	public static boolean isIgnoreAddCommonScript(HttpServletRequest request) {
		return request.getAttribute(IGNORE_ADD_COMMON_SCRIPT) != null;
	}

	public static void setChildrenScriptKeyToRequestAttribute(
			HttpServletRequest request, String key) {
		if (CommonJsSettings.instance().childrenScriptPath.containsKey(key)) {
			request.setAttribute(COMMON_SCRIPT_CHILDREN, key);
		}
	}

	public static String[] getChildrenScriptFromRequestAttribute(
			HttpServletRequest request) {
		String key = (String) request.getAttribute(COMMON_SCRIPT_CHILDREN);
		if (key != null) {
			return CommonJsSettings.instance().childrenScriptPath.get(key);
		}
		return null;
	}

	public static String[] getChildrenScriptFromRequestPath(
			HttpServletRequest request) {
		String key = UrlPathUtil.getServletPath(request);
		if (!isIgnoreAddCommonScript(request)) {
			return CommonJsSettings.instance().childrenScriptPath.get(key);
		} else {
			return null;
		}
	}

	public static String[] getChildrenScriptByKey(String key) {
		if (StringUtils.hasText(key))
			return instance().childrenScriptPath.get(key.trim());
		return null;
	}

	public static String getChildrenScriptKeyFromRequestPath(
			HttpServletRequest request) {
		String key = UrlPathUtil.getServletPath(request);
		if (!isIgnoreAddCommonScript(request)) {
			return CommonJsSettings.instance().childrenScriptPath
					.containsKey(key) ? HtmlUtils.htmlEscape(key) : null;
		} else {
			return null;
		}
	}

	public String getChildrenPrefix() {
		return childrenPrefix;
	}

	public void setChildrenPrefix(String childrenPrefix) {
		this.childrenPrefix = childrenPrefix;
	}
}
