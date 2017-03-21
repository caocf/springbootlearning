package com.sos.tpl.common.web.security;


public interface UserDetailsMapper<T> {
	public T mapping(String  username);
}
