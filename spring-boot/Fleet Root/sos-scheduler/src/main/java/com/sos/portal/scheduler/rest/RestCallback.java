package com.sos.portal.scheduler.rest;

import org.springframework.web.client.RestTemplate;

public interface RestCallback<T> {
	public T callback(RestTemplate restTemplate);
}
