package com.sos.fleet.common.rest;

import org.springframework.web.client.RestTemplate;

public interface RestCallback<T> {
	public T callback(RestTemplate restTemplate);
}
