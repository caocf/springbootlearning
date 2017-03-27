package com.sos.fleet.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sos.fleet.common.constants.OperationKeys;
import com.sos.fleet.common.constants.OperationTypeKeys;
import com.sos.fleet.common.constants.SessionKeys;
import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.exception.SecCodeValidateException;
import com.sos.fleet.common.util.OperationUtil;
import com.sos.fleet.common.util.SessionUtils;

@Component
@ConditionalOnProperty(prefix="filter.SmsFilter", name="enable", havingValue="true", matchIfMissing=false)
public class SmsFilter extends OncePerRequestFilter implements MessageSourceAware{
	
	private static final String SECURITY_CODE = "securityCode";
	
	Log log = LogFactory.getLog(SmsFilter.class);
	
//	@Autowired
//	private MessageSource messageSource;
	
	protected MessageSourceAccessor messages;
	
	private RequestMatcher requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(
			"/login", "POST");
	
	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler("/login?error");
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(requiresAuthenticationRequestMatcher.matches(request)) {
			
			try {
				log.info("SmsFilter start...");
				checkSecurityCode(request);
			} catch (AuthenticationException e) {
				failureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	protected void checkSecurityCode(HttpServletRequest request) throws SecCodeValidateException{
		
		String username = SessionUtils.getAttribute(request, SessionKeys.VALIDATE_CODE_USERNAME, String.class);;
		String localCode = request.getParameter(SECURITY_CODE);
		String sendCode = SessionUtils.getAttribute(request, username+SessionKeys.DYNAMIC_CODE, String.class);
		log.debug(localCode+"======"+sendCode+"======="+username);
		if(!localCode.equals(sendCode)) {
			OperationUtil.log(null, OperationKeys.VALIDATE_SMS_CODE, username, StatusKeys.FAILURE, OperationTypeKeys.USERS_MGMT, null);
			throw new SecCodeValidateException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badSecurityCode",
					"Bad SecurityCode"));
		}
	} 
	
	@Override
	public void setMessageSource(MessageSource arg0) {
		// TODO Auto-generated method stub
		messages = new MessageSourceAccessor(arg0);
	}

}
