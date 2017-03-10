package com.sos.fleet.common.exception;

import org.springframework.security.core.AuthenticationException;

public class SecCodeValidateException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecCodeValidateException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

	public SecCodeValidateException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
