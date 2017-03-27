package com.sos.fleet.common.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaValidateException extends AuthenticationException {

	public CaptchaValidateException(String msg) {
		super(msg);
	}
	public CaptchaValidateException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}