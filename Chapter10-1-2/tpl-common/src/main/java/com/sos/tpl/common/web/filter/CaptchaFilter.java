package com.sos.tpl.common.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import cn.apiclub.captcha.Captcha;

import com.sos.tpl.common.settings.CaptchaSettings;
import com.sos.tpl.common.util.SessionUtils;
import com.sos.tpl.common.util.captcha.SimpleCaptcha;
import com.sos.tpl.common.web.exception.CaptchaValidateException;

@Component
@ConditionalOnProperty(prefix="filter.captchaFilter", name="enable", havingValue="true", matchIfMissing=false)
public class CaptchaFilter extends OncePerRequestFilter implements MessageSourceAware{
	Log log = LogFactory.getLog(getClass());
	public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "captcha";
	private RequestMatcher requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(
			"/login", "POST");
	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler("/login?error");
	protected MessageSourceAccessor messages;
	
	protected String obtainCaptcha(HttpServletRequest request) {
		return request.getParameter(SPRING_SECURITY_FORM_CAPTCHA_KEY);
	}
//	public CaptchaFilter() {
//		super();
//	}
//	public CaptchaFilter(MessageSource messageSource) {
//		this();
//		setMessageSource(messageSource);
//	}
	protected void checkCaptcha(HttpServletRequest request)
			throws CaptchaValidateException {
		String captchaValue = obtainCaptcha(request);
		Captcha captcha = getCaptcha(request);
		if (captcha == null
				|| !captcha.getAnswer().equalsIgnoreCase(captchaValue)) {
			throw new CaptchaValidateException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCaptcha",
					"Bad captcha"));
		}
	}

	private Captcha getCaptcha(HttpServletRequest request) {
		
		Captcha captcha = SessionUtils.getAttribute(request, String.format(SimpleCaptcha.SIMPLE_CAPTCHA_SESSION_KEY_FMT,
				CaptchaSettings.LOGIN_CAPTCHA),Captcha.class);
		return captcha;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (requiresAuthenticationRequestMatcher.matches(request)
				&&CaptchaSettings.showCapcha(request)) {
			try {
				checkCaptcha(request);
			} catch (AuthenticationException e) {
				failureHandler.onAuthenticationFailure(request, response, e);
				return;
			}

		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		messages  = new MessageSourceAccessor(messageSource);
	}

}
