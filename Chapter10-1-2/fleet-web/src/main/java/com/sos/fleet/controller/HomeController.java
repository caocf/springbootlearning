package com.sos.fleet.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sos.fleet.common.controller.RestClientController;

@Controller
public class HomeController extends RestClientController {
	
	private static Log log = LogFactory.getLog(HomeController.class);
	 
	@RequestMapping("/")
	public ModelAndView modifyAuthPage(){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("hellokey", "world");
		return mav;
	}
	 
	@RequestMapping("/sayHello")
	public ModelAndView sayHello(){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("hellokey", "arenzhj");
		return mav;
	}
}
