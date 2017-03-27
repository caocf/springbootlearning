package com.sos.fleet.common.security;


public interface UserDetailsMapper<T> {
	public T mapping(String  username);
}
