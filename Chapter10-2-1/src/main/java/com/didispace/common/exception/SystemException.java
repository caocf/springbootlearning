package com.didispace.common.exception;

/**
 * 系统异常,继承自BaseException.
 * 
 */
@SuppressWarnings("serial")
public class SystemException extends BaseException {

	public SystemException() {
		super();
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

}