package com.sos.fleet.common.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.sos.fleet.common.filter.base.FilterPathMatcher;
import com.sos.fleet.common.filter.base.OncePerRequestBaseFilter;
import com.sos.fleet.common.settings.CommonJsSettings;
import com.sos.fleet.common.util.UrlPathUtil;

@Component
@ConditionalOnProperty(prefix = "filter.CommonJsFilter", name = "enable", havingValue = "true", matchIfMissing = true)
public class CommonJsFilter extends OncePerRequestBaseFilter {
	static Log log = LogFactory.getLog(CommonJsFilter.class);
	private CommonJsSettings jsSettings;
	private static final Pattern REGEX_HEADER = Pattern.compile("</[hH][eE][aA][dD][^>]*>");
	private static final String SCRIPT_COMMON = "\n<script id=\"commonScript\" type=\"text/javascript\" src=\"%s\"></script>";
	private static final String SCRIPT_CHILDREN = "\n<script id=\"commonScript_%s\" type=\"text/javascript\" src=\"%s\"></script>";
	
	@Autowired(required=false)
	private EtagFilter etagFilter;
	
	@Autowired
	FilterPathMatcher filterPathMatcher;
	@Override
	public void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletResponse responseToUse = response;
		if (!(response instanceof ContentCachingResponseWrapper)) {
			responseToUse = new ContentCachingResponseWrapper(response);
		}

		filterChain.doFilter(request, responseToUse);
		updateResponse(request, responseToUse);
	}

	private void updateResponse(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ContentCachingResponseWrapper responseWrapper = WebUtils
				.getNativeResponse(response,
						ContentCachingResponseWrapper.class);

		HttpServletResponse rawResponse = (HttpServletResponse) responseWrapper
				.getResponse();
		byte[] body = responseWrapper.getContentAsByteArray();
		String content;
		String charset; 
		try {
			content = new String(body, charset=rawResponse.getCharacterEncoding());
		} catch (UnsupportedEncodingException e) {
			content = new String(body, charset="UTF-8");
		}
		try{
			if(isAddScript(request, rawResponse)){
				content = getScriptContent(request,content);
			}
		}catch(NotFoundHeadException e){
			log.info("Not found head in response Body, Skiped add common script");
		}
		if(!rawResponse.isCommitted()){
			rawResponse.setContentLength(content.getBytes().length);
		}
		if(etagFilter!=null&&filterPathMatcher.match(request, rawResponse,etagFilter)){
			responseWrapper.resetBuffer();
			StreamUtils.copy(content, Charset.forName(charset), responseWrapper.getOutputStream());
		}else{
			StreamUtils.copy(content, Charset.forName(charset), rawResponse.getOutputStream());
		}
		
	}

	public boolean isAsyncDispatch(HttpServletRequest request) {
		return WebAsyncUtils.getAsyncManager(request).hasConcurrentResult();
	}

	private static class NotFoundHeadException extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = -2136421856983206037L;
		
	}
	private String getScriptContent(HttpServletRequest request, String content) {
		Matcher matcher = REGEX_HEADER.matcher(content);
		if(!matcher.find()){
			throw new NotFoundHeadException();
		}
		if (isAddToBottom()) {
			content+=getScript(request);
		} else {
			String startStr  = content.substring(0,matcher.start());
			String endStr  = content.substring(matcher.start());
			StringBuilder sb = new StringBuilder(0);
			sb.append(startStr)
			.append(getScript(request))
			.append(endStr);
			content = sb.toString();
			sb = null;
			startStr = null;
			endStr = null;
		}
		return content;
	}
	private String getScript(HttpServletRequest request) {
		String key = CommonJsSettings.getChildrenScriptKeyFromRequestPath(request);
		String fileName = String.format("%s"+CommonJsSettings.SCRIPT_URL, UrlPathUtil.getContextPath(request));
		StringBuilder sb = new StringBuilder(String.format(SCRIPT_COMMON, fileName));
		if(key!=null){
			CommonJsSettings.setChildrenScriptKeyToRequestAttribute(request, key);
			String[] children = CommonJsSettings.getChildrenScriptByKey(key);
			if(children!=null){
				for (int i = 0; i < children.length; i++) {
					sb.append(String.format(SCRIPT_CHILDREN,key,CommonJsSettings.instance().getChildrenPrefix()+children[i]));
				}
			}
		}
		return sb.toString();
	}

	/*private boolean isRequestScript(HttpServletRequest request) {
		String scriptURI = String.format(SCRIPT_PATH, request.getContextPath());
		return scriptURI.equals(request.getRequestURI());
	}

	private boolean hasChidrenScript(String key) {
		if (!StringUtils.hasText(key))
			return false;
		return jsSettings.getChildrenScriptPath()
				.containsKey(key.toLowerCase());
	}*/

	private boolean isAddScript(HttpServletRequest request,
			HttpServletResponse response) {
		String contentType = response.getContentType();
		return !CommonJsSettings.isIgnoreAddCommonScript(request)
				&& CommonJsSettings.matchContentType(contentType);
	}

	private boolean isAddToBottom() {
		return jsSettings.getAppendTo().equalsIgnoreCase("bottom");
	}
	
	
	
	@Bean(name="commonJsFilterRegistration")
	@ConditionalOnClass(name="javax.servlet.DispatcherType")
	public FilterRegistrationBean inactiveRegister(){
		return super.inactiveRegister();
	}

	@Override
	protected void initFilterBean() throws ServletException {
		if(etagFilter!=null&&etagFilter.getOrder()>=this.jsSettings.getOrder()){
			throw new ServletException(String.format("Etag's order[%d] can't less than CommonJsFilter's order[%d]", etagFilter.getOrder(),this.jsSettings.getOrder()));
		}
	}

	@Autowired
	public void setJsSettings(CommonJsSettings jsSettings) {
		filterProperties = this.jsSettings = jsSettings;
	}

}
