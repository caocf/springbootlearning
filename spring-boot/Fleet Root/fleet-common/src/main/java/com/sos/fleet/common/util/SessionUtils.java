package com.sos.fleet.common.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
	public static void setAttribute(HttpServletRequest request, String key,Serializable serializable){
		request.getSession().setAttribute(key, serializable);
	}
	public static Serializable getAttribute(HttpServletRequest request, String key){
		Object obj = request.getSession().getAttribute(key);
		if(obj instanceof Serializable){
			return (Serializable) obj;
		}
		throw new RuntimeException("The key of attributes of session is not a instance of serializable.");
	}
	@SuppressWarnings("unchecked")
	public static <T> T getAttribute(HttpServletRequest request, String key,Class<T> clazz){
		Object obj = request.getSession().getAttribute(key);
		return (T) obj;
	}
	
	public static void removeAttribute(HttpServletRequest request, String key){
		request.getSession().removeAttribute(key);
	}
}
