package com.sos.tpl.common.web.exception;

public class ServiceException extends RuntimeException {
	/** 
	 * @属性 serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 8800509240122625517L;

	public ServiceException() {
		super();

	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
