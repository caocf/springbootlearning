package com.didispace.common.mvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.didispace.common.mvc.rest.RestClient;

public abstract class RestClientController extends BaseController {
	static Log log = LogFactory.getLog(RestClientController.class);
	@Autowired
	protected RestClient restClient;
}