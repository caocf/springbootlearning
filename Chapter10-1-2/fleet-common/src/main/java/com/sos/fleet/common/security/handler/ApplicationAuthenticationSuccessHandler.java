package com.sos.fleet.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sos.fleet.common.constants.OperationKeys;
import com.sos.fleet.common.constants.OperationTypeKeys;
import com.sos.fleet.common.constants.SessionKeys;
import com.sos.fleet.common.domain.LoginLogDomain;
import com.sos.fleet.common.filter.SmsFilter;
import com.sos.fleet.common.util.ClientUtil;
import com.sos.fleet.common.util.OperationUtil;
import com.sos.fleet.common.util.SecurityUtil;
import com.sos.fleet.common.util.SessionUtils;

@Component
public class  ApplicationAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	static Log log = LogFactory.getLog(ApplicationAuthenticationSuccessHandler.class);
	
	@Value("${security.defaultTargetUrl:}")
	private String defaultTagetUrl;
	
	@Autowired(required=false)
	private SmsFilter smsFilter;

//	@Autowired
//	private LoginSuccessPostService loginSuccessPostService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
//		loginSuccessPostService.save(createLoginLog(request, response, authentication));
		
		if(smsFilter != null) {
			String username = SessionUtils.getAttribute(request, SessionKeys.VALIDATE_CODE_USERNAME, String.class);;
			SessionUtils.removeAttribute(request, username+SessionKeys.DYNAMIC_CODE);
			OperationUtil.log(null, OperationKeys.VALIDATE_SMS_CODE, username, OperationTypeKeys.USERS_MGMT, null);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	private LoginLogDomain createLoginLog(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication){
		String clientIp = ClientUtil.getClientIp(request);
		log.debug("client IP: " + clientIp);
		String userRole = SecurityUtil.getUserRole(authentication);
		log.debug("user role: " + userRole);
		Long userId = SecurityUtil.getUserId();
		log.debug("user id: " + userId);
		return new LoginLogDomain(null,null,null,clientIp, userRole, null);
	}

	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		if(StringUtils.hasText(defaultTagetUrl)){
			setDefaultTargetUrl(defaultTagetUrl);
		}
		return super.determineTargetUrl(request, response);
	}
	
}
