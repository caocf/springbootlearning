package com.didispace.common.mvc.rest;

import java.net.URI;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {
//	@Autowired
//	protected WebServiceURLSettings wsUrlSettings;
	
	private static RestClient REST_CLIENT;
	
	@PostConstruct
	public void init(){
		REST_CLIENT = this;
	}
	
	public static RestClient instance(){
		return REST_CLIENT;
	}

	@Autowired
	protected RestTemplate restTemplate;

	@Bean
	protected RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	public <T> T getForObject(String url, Class<T> responseType,
			Object... urlVariables) throws RestClientException {
		return restTemplate.getForObject(url, responseType, urlVariables);
	}

	public <T> T getForObject(String url,
			ParameterizedTypeReference<T> responseType, Object... urlVariables)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.GET, null, responseType,
				urlVariables).getBody();
	}

	public <T> T getForObject(String url, Class<T> responseType,
			Map<String, ?> urlVariables) throws RestClientException {
		return restTemplate.getForObject(url, responseType, urlVariables);
	}

	public <T> T getForObject(String url,
			ParameterizedTypeReference<T> responseType,
			Map<String, ?> urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.GET, null, responseType,
				urlVariables).getBody();
	}

	public <T> T getForObject(URI url, Class<T> responseType)
			throws RestClientException {
		return restTemplate.getForObject(url, responseType);
	}

	public <T> T getForObject(URI url,
			ParameterizedTypeReference<T> responseType)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.GET, null, responseType)
				.getBody();
	}

	/*public <T> ResponseEntity<T> getForEntity(String url,
			Class<T> responseType, Object... urlVariables)
			throws RestClientException {
		return restTemplate.getForEntity(url, responseType, urlVariables);
	}

	public <T> ResponseEntity<T> getForEntity(String url,
			Class<T> responseType, Map<String, ?> urlVariables)
			throws RestClientException {
		return restTemplate.getForEntity(url, responseType, urlVariables);
	}

	public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType)
			throws RestClientException {
		return restTemplate.getForEntity(url, responseType);
	}

	public <T> ResponseEntity<T> getForEntity(URI url,
			ParameterizedTypeReference<T> responseType)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.GET, null, responseType);
	}*/

	public <T> T postForObject(String url, Object request,
			Class<T> responseType, Object... uriVariables)
			throws RestClientException {
		return restTemplate.postForObject(url, request, responseType,
				uriVariables);
	}

	public <T> T postForObject(String url, Object request,
			ParameterizedTypeReference<T> responseType, Object... uriVariables)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.POST,
				new HttpEntity<Object>(request), responseType, uriVariables)
				.getBody();
	}

	public <T> T postForObject(String url, Object request,
			Class<T> responseType, Map<String, ?> uriVariables)
			throws RestClientException {
		return restTemplate.postForObject(url, request, responseType,
				uriVariables);
	}

	public <T> T postForObject(String url, Object request,
			ParameterizedTypeReference<T> responseType,
			Map<String, ?> uriVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.POST,
				new HttpEntity<Object>(request), responseType, uriVariables)
				.getBody();
	}

	public <T> T postForObject(URI url, Object request, Class<T> responseType)
			throws RestClientException {
		return restTemplate.postForObject(url, request, responseType);
	}

	public <T> T postForObject(URI url, Object request,
			ParameterizedTypeReference<T> responseType)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.POST,
				new HttpEntity<Object>(request), responseType).getBody();
	}

	/*public <T> ResponseEntity<T> postForEntity(String url, Object request,
			Class<T> responseType, Object... uriVariables)
			throws RestClientException {
		return restTemplate.postForEntity(url, request, responseType,
				uriVariables);
	}

	public <T> ResponseEntity<T> postForEntity(String url, Object request,
			ParameterizedTypeReference<T> responseType, Object... uriVariables)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.POST,
				new HttpEntity<Object>(request), responseType, uriVariables);
	}

	public <T> ResponseEntity<T> postForEntity(String url, Object request,
			Class<T> responseType, Map<String, ?> uriVariables)
			throws RestClientException {
		return restTemplate.postForEntity(url, request, responseType,
				uriVariables);
	}

	public <T> ResponseEntity<T> postForEntity(String url, Object request,
			ParameterizedTypeReference<T> responseType,
			Map<String, ?> uriVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.POST,
				new HttpEntity<Object>(request), responseType, uriVariables);
	}

	public <T> ResponseEntity<T> postForEntity(URI url, Object request,
			Class<T> responseType) throws RestClientException {
		return restTemplate.postForEntity(url, request, responseType);
	}

	public <T> ResponseEntity<T> postForEntity(URI url, Object request,
			ParameterizedTypeReference<T> responseType)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.POST,
				new HttpEntity<Object>(request), responseType);
	}*/

	public <T> T put(String url, Object request,
			ParameterizedTypeReference<T> responseType, Object... urlVariables)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.PUT,
				new HttpEntity<Object>(request), responseType, urlVariables)
				.getBody();
	}

	public <T> T put(String url, Object request, Class<T> responseType,
			Object... urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.PUT,
				new HttpEntity<Object>(request), responseType, urlVariables)
				.getBody();
	}

	public <T> T put(String url, Object request, Class<T> responseType,
			Map<String, ?> urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.PUT, null, responseType,
				urlVariables).getBody();
	}

	public <T> T put(String url, Object request,
			ParameterizedTypeReference<T> responseType,
			Map<String, ?> urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.PUT, null, responseType,
				urlVariables).getBody();
	}

	public <T> T delete(String url, Class<T> responseType,
			Object... urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null,
				responseType, urlVariables).getBody();
	}

	public <T> T delete(String url, ParameterizedTypeReference<T> reponseType,
			Object... urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, reponseType,
				urlVariables).getBody();
	}

	public <T> T delete(String url, ParameterizedTypeReference<T> reponseType,
			Map<String, ?> urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, reponseType,
				urlVariables).getBody();
	}

	public <T> T delete(String url, Class<T> reponseType,
			Map<String, ?> urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, reponseType,
				urlVariables).getBody();
	}

	public <T> T delete(URI url, ParameterizedTypeReference<T> reponseType)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, reponseType)
				.getBody();
	}

	public <T> T delete(URI url, Class<T> reponseType)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, reponseType)
				.getBody();
	}

	/*<T> T deleteForEntity(String url, Class<T> responseType,
			Object... urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null,
				responseType, urlVariables).getBody();
	}

	<T> ResponseEntity<T> deleteForEntity(String url,
			ParameterizedTypeReference<T> reponseType, Object... urlVariables)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, reponseType,
				urlVariables);
	}

	<T> ResponseEntity<T> deleteForEntity(String url,
			ParameterizedTypeReference<T> reponseType,
			Map<String, ?> urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, reponseType,
				urlVariables);
	}

	<T> ResponseEntity<T> deleteForEntity(String url, Class<T> reponseType,
			Map<String, ?> urlVariables) throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, reponseType,
				urlVariables);
	}

	<T> ResponseEntity<T> deleteForEntity(URI url, Class<T> reponseType)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, reponseType);
	}

	<T> ResponseEntity<T> deleteForEntity(URI url,
			ParameterizedTypeReference<T> reponseType)
			throws RestClientException {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, reponseType);
	}*/

	public <T> T exchange(RestCallback<T> callback) {
		return callback.callback(restTemplate);
	}

}