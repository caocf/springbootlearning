package com.sos.tpl.common.web.mvc.rest;

import org.springframework.web.client.RestTemplate;

public interface RestCallback<T> {
	public T callback(RestTemplate restTemplate);
}
