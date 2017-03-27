package com.sos.fleet.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.sos.fleet.common.settings.CaptchaSettings;

public class ApplicationAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		CaptchaSettings.preLoginFailure(request);
		super.onAuthenticationFailure(request, response, exception);
	}

	public ApplicationAuthenticationFailureHandler() {
		super();
	}

	public ApplicationAuthenticationFailureHandler(String defaultFailureUrl) {
		super(defaultFailureUrl);
	}

}
