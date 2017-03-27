package com.sos.fleet.common.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="common")
public class CommonController extends BaseController /*WebContentGenerator*/ implements ApplicationController{
	/*private static final String HEADER_PRAGMA = "Pragma";
	@Value("${common.resource.lastModified}")
	private boolean lastModified;
	@Autowired
	public CommonController(@Value("${spring.resources.cache-period}")int cacheSeconds) {
		super();
		setCacheSeconds(cacheSeconds);
	}
	private void cachePage(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		checkAndPrepare(request, response, lastModified);
		response.setHeader(HEADER_PRAGMA, "");
	}
	*/

	@RequestMapping(value="script",produces="text/javascript;charset=UTF-8")
	public String javascript(HttpServletRequest request, HttpServletResponse response) throws ServletException{
//		cachePage(request, response);ContentNegotiatingViewResolver
		return "js/fleet";
	}
	@RequestMapping(value="publicjs",produces="text/javascript;charset=UTF-8")
	public String publicjs(HttpServletRequest request, HttpServletResponse response) throws ServletException{
//		cachePage(request, response);ContentNegotiatingViewResolver
		return "js/publicjs";
	}
}
